package io.vertx.core.impl.launcher.commands;

import io.vertx.core.cli.annotations.Description;
import io.vertx.core.cli.annotations.Name;
import io.vertx.core.cli.annotations.Summary;
import io.vertx.core.spi.launcher.DefaultCommand;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

@Name(BeanDefinitionParserDelegate.LIST_ELEMENT)
@Summary("List vert.x applications")
@Description("List all vert.x applications launched with the `start` command")
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/launcher/commands/ListCommand.class */
public class ListCommand extends DefaultCommand {
    private static final Pattern PS = Pattern.compile("-Dvertx.id=(.*)\\s*");
    private static final Pattern FAT_JAR_EXTRACTION = Pattern.compile("-jar (\\S*)");
    private static final Pattern VERTICLE_EXTRACTION = Pattern.compile("run (\\S*)");

    @Override // io.vertx.core.spi.launcher.Command
    public void run() {
        this.out.println("Listing vert.x applications...");
        List<String> cmd = new ArrayList<>();
        if (!ExecUtils.isWindows()) {
            try {
                cmd.add(OperatorName.SHADING_FILL);
                cmd.add("-c");
                cmd.add("ps ax | grep \"vertx.id=\"");
                dumpFoundVertxApplications(cmd);
                return;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace(this.out);
                return;
            } catch (Exception e2) {
                e2.printStackTrace(this.out);
                return;
            }
        }
        try {
            cmd.add("WMIC");
            cmd.add("PROCESS");
            cmd.add("WHERE");
            cmd.add("CommandLine like '%java.exe%'");
            cmd.add("GET");
            cmd.add("CommandLine");
            cmd.add("/VALUE");
            dumpFoundVertxApplications(cmd);
        } catch (InterruptedException e3) {
            Thread.currentThread().interrupt();
            e3.printStackTrace(this.out);
        } catch (Exception e4) {
            e4.printStackTrace(this.out);
        }
    }

    private void dumpFoundVertxApplications(List<String> cmd) throws InterruptedException, IOException {
        boolean none = true;
        Process process = new ProcessBuilder(cmd).start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            Matcher matcher = PS.matcher(line);
            if (matcher.find()) {
                String id = matcher.group(1);
                String details = extractApplicationDetails(line);
                this.out.println(id + "\t" + details);
                none = false;
            }
        }
        process.waitFor();
        reader.close();
        if (none) {
            this.out.println("No vert.x application found.");
        }
    }

    protected static String extractApplicationDetails(String line) {
        Matcher matcher = FAT_JAR_EXTRACTION.matcher(line);
        if (matcher.find()) {
            return matcher.group(1);
        }
        Matcher matcher2 = VERTICLE_EXTRACTION.matcher(line);
        if (matcher2.find()) {
            return matcher2.group(1);
        }
        return "";
    }
}
