package com.htmake.reader.api.controller;

import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.WebClient;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import com.htmake.reader.api.ReturnData;
import com.htmake.reader.entity.License;
import io.legado.app.utils.ACache;

public final class LicenseController extends BaseController {

    public LicenseController(CoroutineContext context) {
        super(context);
    }

    // Methods
    public final Object getLicense(RoutingContext ctx, Continuation<? super ReturnData> continuation) {
        License license = new License();
        license.setHost("*");
        license.setUserMaxLimit(999999);
        license.setExpiredAt(Long.MAX_VALUE); // Permanent
        license.setOpenApi(true);
        license.setSimpleWebExpiredAt(Long.MAX_VALUE); // Kindle Unlimited
        license.setInstances(0);
        license.setType("default");
        license.setId("unlimited");
        license.setCode("unlimited");
        license.setVerified(true);
        license.setVerifyTime(System.currentTimeMillis());

        ReturnData rd = new ReturnData();
        rd.setData(license, null);
        return rd;
    }

    public final Object importLicense(RoutingContext ctx, Continuation<? super kotlin.Unit> continuation) {
        // Accept any input, do nothing or pretend success
        return kotlin.Unit.INSTANCE;
    }

    public final Object generateKeys(RoutingContext ctx, Continuation<? super ReturnData> continuation) {
         ReturnData rd = new ReturnData();
         rd.setData("fake_keys", null);
         return rd;
    }

    public final Object generateLicense(RoutingContext ctx, Continuation<? super ReturnData> continuation) {
         ReturnData rd = new ReturnData();
         // Maybe construct a fake license here too if needed
         return rd;
    }

    public final Object isHostValid(RoutingContext ctx, Continuation<? super ReturnData> continuation) {
        ReturnData rd = new ReturnData();
        rd.setData(true, null);
        return rd;
    }

    public final Object decryptLicense(RoutingContext ctx, Continuation<? super ReturnData> continuation) {
        ReturnData rd = new ReturnData();
        return rd;
    }

    public final Object activateLicense(RoutingContext ctx, Continuation<? super ReturnData> continuation) {
        ReturnData rd = new ReturnData();
        rd.setData(true, "Activation Successful");
        return rd;
    }

    public final Object isLicenseValid(RoutingContext ctx, Continuation<? super ReturnData> continuation) {
         ReturnData rd = new ReturnData();
         rd.setData(true, null);
         return rd;
    }

    public final Object checkLicense(License license, Continuation<? super kotlin.Unit> continuation) {
        return kotlin.Unit.INSTANCE;
    }

    public final Object sendCodeToEmail(RoutingContext ctx, Continuation<? super ReturnData> continuation) {
         ReturnData rd = new ReturnData();
         rd.setData(true, "Code sent (fake)");
         return rd;
    }

    public final Object supplyLicense(RoutingContext ctx, Continuation<? super ReturnData> continuation) {
         ReturnData rd = new ReturnData();
         rd.setData(true, "Supplied");
         return rd;
    }

    // Stub for fields/accessors if needed by other classes?
    // Based on javap, getBackupFileNames and getWebClient are there.
    public final String[] getBackupFileNames() {
        return new String[]{};
    }

    // access$getWebClient is static synthetic, might not be needed unless called from inner classes.
    // But since we removed inner classes, it should be fine.
}
