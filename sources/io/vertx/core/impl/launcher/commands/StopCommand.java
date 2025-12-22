package io.vertx.core.impl.launcher.commands;

import io.legado.app.constant.Action;
import io.vertx.core.cli.annotations.Argument;
import io.vertx.core.cli.annotations.Description;
import io.vertx.core.cli.annotations.Hidden;
import io.vertx.core.cli.annotations.Name;
import io.vertx.core.cli.annotations.Option;
import io.vertx.core.cli.annotations.Summary;
import io.vertx.core.spi.launcher.DefaultCommand;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

@Name(Action.stop)
@Summary("Stop a vert.x application")
@Description("This command stops a vert.x application started with the `start` command. The command requires the application id as argument. Use the `list` command to get the list of applications")
/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/impl/launcher/commands/StopCommand.class */
public class StopCommand extends DefaultCommand {
    private String id;
    private boolean redeploy;
    private static final Pattern PS = Pattern.compile("([0-9]+)\\s.*-Dvertx.id=.*");

    @Argument(index = 0, argName = "vertx.id", required = false)
    @Description("The vert.x application id")
    public void setApplicationId(String id) {
        this.id = id;
    }

    @Option(longName = "redeploy", flag = true)
    @Hidden
    public void setRedeploy(boolean redeploy) {
        this.redeploy = redeploy;
    }

    @Override // io.vertx.core.spi.launcher.Command
    public void run() throws InterruptedException, IOException {
        if (this.id == null) {
            this.out.println("Application id not specified...");
            this.executionContext.execute(BeanDefinitionParserDelegate.LIST_ELEMENT, new String[0]);
            return;
        }
        this.out.println("Stopping vert.x application '" + this.id + OperatorName.SHOW_TEXT_LINE);
        if (ExecUtils.isWindows()) {
            terminateWindowsApplication();
        } else {
            terminateLinuxApplication();
        }
    }

    private void terminateLinuxApplication() throws InterruptedException, IOException {
        String pid = pid();
        if (pid == null) {
            this.out.println("Cannot find process for application using the id '" + this.id + "'.");
            if (!this.redeploy) {
                ExecUtils.exitBecauseOfProcessIssue();
                return;
            }
            return;
        }
        List<String> cmd = new ArrayList<>();
        cmd.add("kill");
        cmd.add(pid);
        try {
            int result = new ProcessBuilder(cmd).start().waitFor();
            this.out.println("Application '" + this.id + "' terminated with status " + result);
            if (!this.redeploy) {
                ExecUtils.exit(result);
            }
        } catch (Exception e) {
            this.out.println("Failed to stop application '" + this.id + OperatorName.SHOW_TEXT_LINE);
            e.printStackTrace(this.out);
            if (!this.redeploy) {
                ExecUtils.exitBecauseOfProcessIssue();
            }
        }
    }

    private void terminateWindowsApplication() throws InterruptedException, IOException {
        List<String> cmd = Arrays.asList("WMIC", "PROCESS", "WHERE", "CommandLine like '%vertx.id=" + this.id + "%'", "CALL", "TERMINATE");
        try {
            Process process = new ProcessBuilder(cmd).start();
            int result = process.waitFor();
            this.out.println("Application '" + this.id + "' terminated with status " + result);
            if (!this.redeploy) {
                ExecUtils.exit(result);
            }
        } catch (Exception e) {
            this.out.println("Failed to stop application '" + this.id + OperatorName.SHOW_TEXT_LINE);
            e.printStackTrace(this.out);
            if (!this.redeploy) {
                ExecUtils.exitBecauseOfProcessIssue();
            }
        }
    }

    private String pid() throws InterruptedException, IOException {
        Matcher matcher;
        try {
            Process process = new ProcessBuilder((List<String>) Arrays.asList(OperatorName.SHADING_FILL, "-c", "ps ax | grep \"" + this.id + OperatorName.SHOW_TEXT_LINE_AND_SPACE)).start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            do {
                String line = reader.readLine();
                if (line != null) {
                    matcher = PS.matcher(line);
                } else {
                    process.waitFor();
                    reader.close();
                    return null;
                }
            } while (!matcher.find());
            return matcher.group(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace(this.out);
            return null;
        } catch (Exception e2) {
            e2.printStackTrace(this.out);
            return null;
        }
    }
}
