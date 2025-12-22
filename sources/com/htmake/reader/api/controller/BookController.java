package com.htmake.reader.api.controller;

import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.joran.util.beans.BeanUtil;
import ch.qos.logback.core.pattern.parser.Parser;
import cn.hutool.core.img.ImgUtil;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.reflect.TypeToken;
import com.htmake.reader.api.ReturnData;
import com.htmake.reader.entity.User;
import com.htmake.reader.lib.tts.constant.TtsStyleEnum;
import com.htmake.reader.lib.tts.constant.VoiceEnum;
import com.htmake.reader.lib.tts.model.SSML;
import com.htmake.reader.lib.tts.service.TTSService;
import com.htmake.reader.utils.ExtKt;
import com.htmake.reader.utils.SpringContextUtils;
import io.legado.app.constant.AppPattern;
import io.legado.app.constant.BookType;
import io.legado.app.data.entities.Book;
import io.legado.app.data.entities.BookChapter;
import io.legado.app.data.entities.BookSource;
import io.legado.app.data.entities.HttpTTS;
import io.legado.app.data.entities.SearchBook;
import io.legado.app.help.BookHelp;
import io.legado.app.help.DefaultData;
import io.legado.app.utils.ACache;
import io.legado.app.utils.FileUtils;
import io.legado.app.utils.MD5Utils;
import io.legado.app.utils.NetworkUtils;
import io.legado.app.utils.ZipUtils;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.CaseInsensitiveHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.regex.Matcher;
import javax.imageio.ImageIO;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import kotlin.time.DurationKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.slf4j.MDCContext;
import kotlinx.coroutines.sync.Mutex;
import me.ag2s.epublib.domain.Author;
import me.ag2s.epublib.domain.Date;
import me.ag2s.epublib.domain.EpubBook;
import me.ag2s.epublib.domain.FileResourceProvider;
import me.ag2s.epublib.domain.LazyResource;
import me.ag2s.epublib.domain.Resource;
import me.ag2s.epublib.domain.Resources;
import me.ag2s.epublib.epub.NCXDocumentV2;
import me.ag2s.epublib.util.ResourceUtil;
import org.apache.fontbox.ttf.OS2WindowsMetricsTable;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.aop.framework.autoproxy.target.QuickTargetSourceCreator;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;

/* compiled from: BookController.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u008a\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010��\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010#\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0007\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018��2\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J,\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00072\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u001d\u001a\u00020\u0007H\u0002J\u0019\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J\u0019\u0010\u001f\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J!\u0010 \u001a\u00020\u00182\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u001d\u001a\u00020\u0007H\u0086@ø\u0001��¢\u0006\u0002\u0010#J\u0019\u0010 \u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J\u0019\u0010$\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J \u0010%\u001a\u00020\u00182\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u000f2\b\b\u0002\u0010)\u001a\u00020*J\u0018\u0010+\u001a\u00020*2\u0006\u0010&\u001a\u00020'2\b\b\u0002\u0010)\u001a\u00020*J/\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010.\u001a\u00020\u00072\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u0007H\u0086@ø\u0001��¢\u0006\u0002\u00100J\u0019\u00101\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J\u0019\u00102\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J\u0019\u00103\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J7\u00104\u001a\u0004\u0018\u00010'2\u0006\u0010&\u001a\u00020'2\u0006\u0010\u001d\u001a\u00020\u00072\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020'06H\u0086@ø\u0001��¢\u0006\u0002\u00107J\u0019\u00108\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J\u0019\u00109\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J3\u0010:\u001a\u00020-2\u0006\u0010;\u001a\u00020-2\u0006\u0010&\u001a\u00020'2\b\u0010<\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001d\u001a\u00020\u0007H\u0082@ø\u0001��¢\u0006\u0002\u0010=J1\u0010>\u001a\u00020-2\u0006\u0010;\u001a\u00020-2\u0006\u0010?\u001a\u00020'2\u0006\u0010<\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0007H\u0086@ø\u0001��¢\u0006\u0002\u0010=J\u0018\u0010@\u001a\u00020*2\u0006\u0010&\u001a\u00020'2\b\b\u0002\u0010)\u001a\u00020*J\u0018\u0010A\u001a\u00020*2\u0006\u0010&\u001a\u00020'2\b\b\u0002\u0010)\u001a\u00020*J(\u0010B\u001a\u00020\u00072\u0006\u0010C\u001a\u00020D2\u0006\u0010&\u001a\u00020'2\u0006\u0010E\u001a\u00020\u00072\u0006\u0010F\u001a\u00020GH\u0002J\u0099\u0001\u0010H\u001a\u00020\u00182\u0006\u0010&\u001a\u00020'2\u0006\u0010I\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u00072n\u0010J\u001aj\u0012\u0013\u0012\u00110\u0007¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(N\u0012K\u0012I\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00070P\u0018\u00010Oj\u001c\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00070P\u0018\u0001`Q¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(R\u0012\u0004\u0012\u00020\u00180KH\u0082@ø\u0001��¢\u0006\u0002\u0010SJ\u0019\u0010T\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J\u0010\u0010U\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u0007H\u0002J\u0019\u0010V\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J\u0019\u0010W\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J\u0019\u0010X\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J)\u0010Y\u001a\b\u0012\u0004\u0012\u00020'0Z2\b\b\u0002\u0010[\u001a\u00020*2\u0006\u0010\u001d\u001a\u00020\u0007H\u0086@ø\u0001��¢\u0006\u0002\u0010\\J/\u0010]\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0019\u001a\u00020\u00072\b\b\u0002\u0010^\u001a\u00020*H\u0086@ø\u0001��¢\u0006\u0002\u0010_J\u0018\u0010`\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0007J\u0019\u0010a\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J\u001c\u0010b\u001a\b\u0012\u0004\u0012\u00020\u000f0c2\u0006\u0010?\u001a\u00020'2\u0006\u0010\u001d\u001a\u00020\u0007J\u0016\u0010d\u001a\u00020-2\u0006\u0010?\u001a\u00020'2\u0006\u0010\u001d\u001a\u00020\u0007J\u0019\u0010e\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J\u0019\u0010f\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J\u0018\u0010g\u001a\u0004\u0018\u00010h2\u0006\u0010M\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0007J\u0010\u0010i\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u0007H\u0002J\u0019\u0010j\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J\u001b\u0010k\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001d\u001a\u00020\u0007H\u0086@ø\u0001��¢\u0006\u0002\u0010lJQ\u0010m\u001a\b\u0012\u0004\u0012\u00020G0Z2\u0006\u0010&\u001a\u00020'2\b\u0010<\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010[\u001a\u00020*2\u0006\u0010\u001d\u001a\u00020\u00072\b\b\u0002\u0010n\u001a\u00020*2\n\b\u0002\u0010o\u001a\u0004\u0018\u00010pH\u0086@ø\u0001��¢\u0006\u0002\u0010qJ,\u0010r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00070s2\u0006\u0010E\u001a\u00020\u00072\u0006\u0010t\u001a\u00020\u000f2\u0006\u0010u\u001a\u00020\u0007H\u0002J\u0019\u0010v\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J\u0018\u0010w\u001a\u0004\u0018\u00010'2\u0006\u0010x\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0007J\u0019\u0010y\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J+\u0010z\u001a\u0004\u0018\u00010{2\u0006\u0010|\u001a\u00020h2\u0006\u0010}\u001a\u00020\u00072\u0006\u0010~\u001a\u00020\u000fH\u0086@ø\u0001��¢\u0006\u0002\u0010\u007fJ\u001a\u0010\u0080\u0001\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J\u001a\u0010\u0081\u0001\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J\u001a\u0010\u0082\u0001\u001a\u00020*2\u0007\u0010<\u001a\u00030\u0083\u00012\u0006\u0010\u001d\u001a\u00020\u0007H\u0002J\u001b\u0010\u0084\u0001\u001a\u00020'2\u0006\u0010&\u001a\u00020'H\u0086@ø\u0001��¢\u0006\u0003\u0010\u0085\u0001J\u001a\u0010\u0086\u0001\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J\u001a\u0010\u0087\u0001\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J\u001a\u0010\u0088\u0001\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J\u001a\u0010\u0089\u0001\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J\u001a\u0010\u008a\u0001\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J\u001a\u0010\u008b\u0001\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J/\u0010\u008c\u0001\u001a\u00020\u00182\u0006\u0010&\u001a\u00020'2\u0006\u0010\u001d\u001a\u00020\u00072\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u0007H\u0086@ø\u0001��¢\u0006\u0003\u0010\u008d\u0001J\u001a\u0010\u008e\u0001\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J(\u0010\u008f\u0001\u001a\b\u0012\u0004\u0012\u00020'0Z2\r\u0010\u0090\u0001\u001a\b\u0012\u0004\u0012\u00020'0ZH\u0086@ø\u0001��¢\u0006\u0003\u0010\u0091\u0001J\u001a\u0010\u0092\u0001\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J,\u0010\u0093\u0001\u001a\u00020\u00182\u0006\u0010&\u001a\u00020'2\u0007\u0010\u0094\u0001\u001a\u00020G2\u0006\u0010\u001d\u001a\u00020\u0007H\u0086@ø\u0001��¢\u0006\u0003\u0010\u0095\u0001J2\u0010\u0096\u0001\u001a\u00020\u00182\u0006\u0010&\u001a\u00020'2\u000e\u0010\u0097\u0001\u001a\t\u0012\u0005\u0012\u00030\u0098\u00010Z2\u0006\u0010\u001d\u001a\u00020\u00072\t\b\u0002\u0010\u0099\u0001\u001a\u00020*J.\u0010\u009a\u0001\u001a\u0010\u0012\u0004\u0012\u00020'\u0012\u0006\u0012\u0004\u0018\u00010\u00070s2\u0007\u0010\u009b\u0001\u001a\u00020'2\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0015J#\u0010\u009c\u0001\u001a\u00020\u00182\u0006\u0010&\u001a\u00020'2\u0006\u0010\u001d\u001a\u00020\u0007H\u0082@ø\u0001��¢\u0006\u0003\u0010\u009d\u0001J?\u0010\u009e\u0001\u001a\u00020\u00182\b\u0010\u009f\u0001\u001a\u00030 \u00012\b\u0010¡\u0001\u001a\u00030¢\u00012\u0006\u0010(\u001a\u00020\u000f2\b\u0010£\u0001\u001a\u00030¤\u00012\u0007\u0010¥\u0001\u001a\u00020\u00072\u0007\u0010¦\u0001\u001a\u00020-J>\u0010§\u0001\u001a\u00020\u00182\u0006\u0010&\u001a\u00020'2\r\u0010¨\u0001\u001a\b\u0012\u0004\u0012\u00020G0Z2\u0006\u0010\u001d\u001a\u00020\u00072\n\b\u0002\u0010o\u001a\u0004\u0018\u00010pH\u0086@ø\u0001��¢\u0006\u0003\u0010©\u0001J,\u0010ª\u0001\u001a\u00020\u00182\u0006\u0010&\u001a\u00020'2\u0007\u0010\u0094\u0001\u001a\u00020G2\u0006\u0010\u001d\u001a\u00020\u0007H\u0086@ø\u0001��¢\u0006\u0003\u0010\u0095\u0001J'\u0010«\u0001\u001a\u00020*2\u0006\u0010\u001d\u001a\u00020\u00072\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u0007H\u0086@ø\u0001��¢\u0006\u0003\u0010¬\u0001J\u001a\u0010\u00ad\u0001\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J\u001a\u0010®\u0001\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J\u001a\u0010¯\u0001\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J\u001a\u0010°\u0001\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J\u001a\u0010±\u0001\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J\u001a\u0010²\u0001\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016JJ\u0010³\u0001\u001a\u0014\u0012\u0005\u0012\u00030\u0098\u00010Oj\t\u0012\u0005\u0012\u00030\u0098\u0001`Q2\u0006\u0010I\u001a\u00020\u00072\u0006\u0010&\u001a\u00020'2\t\b\u0002\u0010´\u0001\u001a\u00020*2\b\b\u0002\u0010\u001d\u001a\u00020\u0007H\u0086@ø\u0001��¢\u0006\u0003\u0010µ\u0001J2\u0010¶\u0001\u001a\t\u0012\u0005\u0012\u00030·\u00010Z2\u0006\u0010&\u001a\u00020'2\u0006\u0010F\u001a\u00020G2\u0006\u0010u\u001a\u00020\u0007H\u0086@ø\u0001��¢\u0006\u0003\u0010\u0095\u0001J+\u0010¸\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0Z2\u0007\u0010¹\u0001\u001a\u00020\u00072\u0007\u0010º\u0001\u001a\u00020\u0007H\u0082@ø\u0001��¢\u0006\u0003\u0010¬\u0001J\u0019\u0010»\u0001\u001a\u00020\u00072\u0006\u0010&\u001a\u00020'2\u0006\u0010C\u001a\u00020DH\u0002J\u001a\u0010¼\u0001\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016J-\u0010½\u0001\u001a\u00020\u00182\u0006\u0010&\u001a\u00020'2\u0006\u0010C\u001a\u00020D2\b\u0010I\u001a\u0004\u0018\u00010\u0007H\u0082@ø\u0001��¢\u0006\u0003\u0010¾\u0001J>\u0010¿\u0001\u001a\u00020\u00182\u0007\u0010À\u0001\u001a\u00020\u00072\u0006\u0010&\u001a\u00020'2\u0006\u0010C\u001a\u00020D2\b\u0010I\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001d\u001a\u00020\u0007H\u0082@ø\u0001��¢\u0006\u0003\u0010Á\u0001J\u0019\u0010Â\u0001\u001a\u00020\u00182\u0006\u0010&\u001a\u00020'2\u0006\u0010C\u001a\u00020DH\u0002J$\u0010Ã\u0001\u001a\u00020\u00182\u0007\u0010Ä\u0001\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0007H\u0086@ø\u0001��¢\u0006\u0003\u0010Å\u0001J$\u0010Æ\u0001\u001a\u00020*2\u0007\u0010Ç\u0001\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0007H\u0086@ø\u0001��¢\u0006\u0003\u0010¬\u0001J\u001a\u0010È\u0001\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016JF\u0010É\u0001\u001a\u00020\u00182\b\u0010Ê\u0001\u001a\u00030Ë\u00012\u0006\u0010N\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u00072\u0017\b\u0002\u0010Ì\u0001\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u001bH\u0086@ø\u0001��¢\u0006\u0003\u0010Í\u0001J>\u0010Î\u0001\u001a\u00020\u00182\b\u0010Ê\u0001\u001a\u00030Ë\u00012\u0006\u0010N\u001a\u00020\u00072\u0017\b\u0002\u0010Ì\u0001\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u001bH\u0086@ø\u0001��¢\u0006\u0003\u0010Ï\u0001J>\u0010Ð\u0001\u001a\u00020\u00182\b\u0010Ê\u0001\u001a\u00030Ë\u00012\u0006\u0010N\u001a\u00020\u00072\u0017\b\u0002\u0010Ì\u0001\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u001bH\u0086@ø\u0001��¢\u0006\u0003\u0010Ï\u0001J!\u0010Ñ\u0001\u001a\u00020\u00072\u0006\u0010&\u001a\u00020'2\u0006\u0010F\u001a\u00020G2\u0006\u0010E\u001a\u00020\u0007H\u0002R!\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082D¢\u0006\u0002\n��R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n��\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006Ò\u0001"}, d2 = {"Lcom/htmake/reader/api/controller/BookController;", "Lcom/htmake/reader/api/controller/BaseController;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "backupFileNames", "", "", "getBackupFileNames", "()[Ljava/lang/String;", "backupFileNames$delegate", "Lkotlin/Lazy;", "bookInfoCache", "Lio/legado/app/utils/ACache;", "concurrentLoopCount", "", "webClient", "Lio/vertx/ext/web/client/WebClient;", "addBookGroupMulti", "Lcom/htmake/reader/api/ReturnData;", CoreConstants.CONTEXT_SCOPE_VALUE, "Lio/vertx/ext/web/RoutingContext;", "(Lio/vertx/ext/web/RoutingContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addInvalidBookSource", "", "sourceUrl", "invalidInfo", "", "", "userNameSpace", "backupToMongodb", "bookSourceDebugSSE", "cacheBookOnServer", "bookUrlList", "Lio/vertx/core/json/JsonArray;", "(Lio/vertx/core/json/JsonArray;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cacheBookSSE", "convertPdfPageToImage", "book", "Lio/legado/app/data/entities/Book;", "index", "force", "", "convertPdfToImage", "createUserBackup", "Ljava/io/File;", "backupDir", "latestZipFilePath", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteBook", "deleteBookCache", "deleteBooks", "editShelfBook", "handler", "Lkotlin/Function1;", "(Lio/legado/app/data/entities/Book;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "exploreBook", "exportBook", "exportToEpub", "exportDir", "bookSource", "(Ljava/io/File;Lio/legado/app/data/entities/Book;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "exportToTxt", "bookInfo", "extractCbz", "extractEpub", "fixPic", "epubBook", "Lme/ag2s/epublib/domain/EpubBook;", "content", NCXDocumentV2.NCXAttributeValues.chapter, "Lio/legado/app/data/entities/BookChapter;", "getAllContents", "bookSourceString", RtspHeaders.Values.APPEND, "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", NCXDocumentV2.NCXTags.text, "Ljava/util/ArrayList;", "Lkotlin/Triple;", "Lkotlin/collections/ArrayList;", "srcList", "(Lio/legado/app/data/entities/Book;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAvailableBookSource", "getBookChaptersCache", "getBookContent", "getBookCover", "getBookInfo", "getBookShelfBooks", "", "refresh", "(ZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBookSourceString", "withExploreUrl", "(Lio/vertx/ext/web/RoutingContext;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBookSourceStringBySourceURLOpt", "getBookshelf", "getCachedChapterContentSet", "", "getChapterCacheDir", "getChapterList", "getChapterListByRule", "getHttpTTSByName", "Lio/legado/app/data/entities/HttpTTS;", "getInvalidBookSourceCache", "getInvalidBookSources", "getLastBackFileFromWebdav", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLocalChapterList", "debugLog", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "(Lio/legado/app/data/entities/Book;Ljava/lang/String;ZLjava/lang/String;ZLkotlinx/coroutines/sync/Mutex;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getResultAndQueryIndex", "Lkotlin/Pair;", "queryIndexInContent", "query", "getShelfBook", "getShelfBookByURL", "url", "getShelfBookWithCacheInfo", "getSpeakStream", "Ljava/io/InputStream;", "httpTts", "speakText", "speechRate", "(Lio/legado/app/data/entities/HttpTTS;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTxtTocRules", "importBookPreview", "isInvalidBookSource", "Lio/legado/app/data/entities/BookSource;", "mergeBookCacheInfo", "(Lio/legado/app/data/entities/Book;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshLocalBook", "removeBookGroupMulti", "restoreFromMongodb", "saveBook", "saveBookConfig", "saveBookContent", "saveBookCover", "(Lio/legado/app/data/entities/Book;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveBookGroupId", "saveBookInfoCache", "bookList", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveBookProgress", "saveBookProgressToWebdav", "bookChapter", "(Lio/legado/app/data/entities/Book;Lio/legado/app/data/entities/BookChapter;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveBookSources", "sourceList", "Lio/legado/app/data/entities/SearchBook;", Parser.REPLACE_CONVERTER_WORD, "saveBookToShelf", "_book", "saveLocalBookCover", "(Lio/legado/app/data/entities/Book;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "savePdfPageToImage", "document", "Lorg/apache/pdfbox/pdmodel/PDDocument;", "renderer", "Lorg/apache/pdfbox/rendering/PDFRenderer;", "targetWidth", "", "imageFormat", "output", "saveShelfBookLatestChapter", "bookChapterList", "(Lio/legado/app/data/entities/Book;Ljava/util/List;Ljava/lang/String;Lkotlinx/coroutines/sync/Mutex;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveShelfBookProgress", "saveToWebdav", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchBook", "searchBookContent", "searchBookMulti", "searchBookMultiSSE", "searchBookSource", "searchBookSourceSSE", "searchBookWithSource", "accurate", "(Ljava/lang/String;Lio/legado/app/data/entities/Book;ZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchChapter", "Lio/legado/app/data/entities/SearchResult;", "searchPosition", "mContent", "pattern", "setAssets", "setBookSource", "setCover", "(Lio/legado/app/data/entities/Book;Lme/ag2s/epublib/domain/EpubBook;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setEpubContent", "contentModel", "(Ljava/lang/String;Lio/legado/app/data/entities/Book;Lme/ag2s/epublib/domain/EpubBook;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setEpubMetadata", "syncBookProgressFromWebdav", "progressFilePath", "(Ljava/lang/Object;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncFromWebdav", "zipFilePath", "textToSpeech", "ttsByApi", "response", "Lio/vertx/core/http/HttpServerResponse;", "options", "(Lio/vertx/core/http/HttpServerResponse;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ttsByEdge", "(Lio/vertx/core/http/HttpServerResponse;Ljava/lang/String;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ttsByTextToSpeechCn", "updateImageLinkInContent", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController.class */
public final class BookController extends BaseController {

    @NotNull
    private ACache bookInfoCache;
    private final int concurrentLoopCount;

    @NotNull
    private WebClient webClient;

    @NotNull
    private final Lazy backupFileNames$delegate;

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {1792, 1803}, i = {0, 0, 0, 1, 1}, s = {"L$0", "L$1", "L$2", "L$2", "L$3"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData", "userNameSpace", "bookJsonArray"}, m = "addBookGroupMulti", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$addBookGroupMulti$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$addBookGroupMulti$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        long J$0;
        int I$0;
        int I$1;
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.addBookGroupMulti(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {3700}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "backupToMongodb", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$backupToMongodb$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$backupToMongodb$1.class */
    static final class C00621 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C00621(Continuation<? super C00621> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.backupToMongodb(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {2617, 2656}, i = {0, 0, 0, 0}, s = {"L$0", "L$1", "L$2", "L$3"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData", "response"}, m = "bookSourceDebugSSE", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$bookSourceDebugSSE$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$bookSourceDebugSSE$1.class */
    static final class C00631 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        /* synthetic */ Object result;
        int label;

        C00631(Continuation<? super C00631> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.bookSourceDebugSSE(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {2785}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "cacheBookOnServer", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$cacheBookOnServer$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$cacheBookOnServer$1.class */
    static final class C00641 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C00641(Continuation<? super C00641> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.cacheBookOnServer(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {2823, 2836, 2840}, i = {1, 1, 1, 1, 1}, s = {"L$5", "L$6", "L$7", "L$8", "I$4"}, n = {"chapterList", "cachedChapterContentSet", "localCacheDir", "chapterInfo", "chapterIndex"}, m = "cacheBookOnServer", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$cacheBookOnServer$3, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$cacheBookOnServer$3.class */
    static final class AnonymousClass3 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        /* synthetic */ Object result;
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.cacheBookOnServer(null, null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {2669, 2706, 2713, 2731}, i = {0, 0, 0, 0, 1, 2, 3, 3}, s = {"L$0", "L$1", "L$2", "L$3", "L$6", "L$6", "L$2", "L$3"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData", "response", "bookSource", "chapterList", "successCount", "failedCount"}, m = "cacheBookSSE", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$cacheBookSSE$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$cacheBookSSE$1.class */
    static final class C00661 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int I$0;
        int I$1;
        /* synthetic */ Object result;
        int label;

        C00661(Continuation<? super C00661> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.cacheBookSSE(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {1838}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "deleteBook", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$deleteBook$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$deleteBook$1.class */
    static final class C00681 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C00681(Continuation<? super C00681> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.deleteBook(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {2859}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "deleteBookCache", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$deleteBookCache$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$deleteBookCache$1.class */
    static final class C00691 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C00691(Continuation<? super C00691> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.deleteBookCache(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {1888}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "deleteBooks", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$deleteBooks$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$deleteBooks$1.class */
    static final class C00701 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C00701(Continuation<? super C00701> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.deleteBooks(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {2236, 2239}, i = {0, 0, 0, 0, 1, 1, 1, 1, 1}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4"}, n = {"this", "book", "userNameSpace", "handler", "this", "book", "userNameSpace", "handler", "mutex"}, m = "editShelfBook", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$editShelfBook$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$editShelfBook$1.class */
    static final class C00711 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        /* synthetic */ Object result;
        int label;

        C00711(Continuation<? super C00711> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.editShelfBook(null, null, null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {727, 728, 745}, i = {0, 0, 0, 1, 1, 1}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData", "this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "exploreBook", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$exploreBook$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$exploreBook$1.class */
    static final class C00721 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C00721(Continuation<? super C00721> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.exploreBook(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {3201, 3242, 3250, 3252}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "exportBook", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$exportBook$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$exportBook$1.class */
    static final class C00731 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int I$0;
        /* synthetic */ Object result;
        int label;

        C00731(Continuation<? super C00731> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.exportBook(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {3356, 3361}, i = {0, 0, 0, 0, 0, 0, 1, 1}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1"}, n = {"this", "book", "bookSource", "userNameSpace", "bookFile", "epubBook", "bookFile", "epubBook"}, m = "exportToEpub", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$exportToEpub$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$exportToEpub$1.class */
    static final class C00741 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        /* synthetic */ Object result;
        int label;

        C00741(Continuation<? super C00741> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.exportToEpub(null, null, null, null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {3264}, i = {0}, s = {"L$0"}, n = {"bookFile"}, m = "exportToTxt", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$exportToTxt$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$exportToTxt$1.class */
    static final class C00751 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        C00751(Continuation<? super C00751> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.exportToTxt(null, null, null, null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {3298}, i = {0, 0, 0, 0}, s = {"L$0", "L$1", "L$2", "L$3"}, n = {"this", "book", "userNameSpace", RtspHeaders.Values.APPEND}, m = "getAllContents", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$getAllContents$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$getAllContents$1.class */
    static final class C00771 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        /* synthetic */ Object result;
        int label;

        C00771(Continuation<? super C00771> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.getAllContents(null, null, null, null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {1280, 1314}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "getAvailableBookSource", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$getAvailableBookSource$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$getAvailableBookSource$1.class */
    static final class C00781 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        /* synthetic */ Object result;
        int label;

        C00781(Continuation<? super C00781> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.getAvailableBookSource(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {456, 485, 502, 512, 512, 513, 518, 520, 632, 636}, i = {0, 0, 0, 6, 7}, s = {"L$0", "L$1", "L$2", "L$6", "L$6"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData", "chapterList", "chapterList"}, m = "getBookContent", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$getBookContent$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$getBookContent$1.class */
    static final class C00811 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        /* synthetic */ Object result;
        int label;

        C00811(Continuation<? super C00811> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.getBookContent(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {147, 156, 158, 163, 163, 167}, i = {0}, s = {"L$4"}, n = {"userNameSpace"}, m = "getBookInfo", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$getBookInfo$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$getBookInfo$1.class */
    static final class C00831 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        /* synthetic */ Object result;
        int label;

        C00831(Continuation<? super C00831> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.getBookInfo(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {1954}, i = {}, s = {}, n = {}, m = "getBookShelfBooks", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$getBookShelfBooks$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$getBookShelfBooks$1.class */
    static final class C00841 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        C00841(Continuation<? super C00841> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.getBookShelfBooks(false, null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {1345, 1356}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "getBookshelf", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$getBookshelf$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$getBookshelf$1.class */
    static final class C00871 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C00871(Continuation<? super C00871> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.getBookshelf(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {353, 379, 382, 387, 387, 389, 391, 407}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "getChapterList", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$getChapterList$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$getChapterList$1.class */
    static final class C00881 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int I$0;
        /* synthetic */ Object result;
        int label;

        C00881(Continuation<? super C00881> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.getChapterList(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {302}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "getChapterListByRule", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$getChapterListByRule$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$getChapterListByRule$1.class */
    static final class C00891 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C00891(Continuation<? super C00891> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.getChapterListByRule(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {112}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "getInvalidBookSources", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$getInvalidBookSources$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$getInvalidBookSources$1.class */
    static final class C00901 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C00901(Continuation<? super C00901> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.getInvalidBookSources(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {2019, 2022, 2033, 2035, 2051}, i = {}, s = {}, n = {}, m = "getLocalChapterList", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$getLocalChapterList$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$getLocalChapterList$1.class */
    static final class C00911 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        boolean Z$0;
        /* synthetic */ Object result;
        int label;

        C00911(Continuation<? super C00911> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.getLocalChapterList(null, null, false, null, false, null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {1362}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "getShelfBook", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$getShelfBook$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$getShelfBook$1.class */
    static final class C00931 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C00931(Continuation<? super C00931> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.getShelfBook(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {3179, 3183}, i = {0, 0, 0, 1}, s = {"L$0", "L$1", "L$2", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData", "userNameSpace"}, m = "getShelfBookWithCacheInfo", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$getShelfBookWithCacheInfo$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$getShelfBookWithCacheInfo$1.class */
    static final class C00941 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C00941(Continuation<? super C00941> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.getShelfBookWithCacheInfo(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {3048}, i = {0, 0, 0, 0, 0, 0, 0}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0"}, n = {"this", "httpTts", "speakText", "downloadErrorNo", "analyzeUrl", "response", "speechRate"}, m = "getSpeakStream", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$getSpeakStream$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$getSpeakStream$1.class */
    static final class C00951 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int I$0;
        /* synthetic */ Object result;
        int label;

        C00951(Continuation<? super C00951> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.getSpeakStream(null, null, 0, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {285}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "getTxtTocRules", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$getTxtTocRules$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$getTxtTocRules$1.class */
    static final class C00961 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C00961(Continuation<? super C00961> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.getTxtTocRules(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {224}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "importBookPreview", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$importBookPreview$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$importBookPreview$1.class */
    static final class C00971 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C00971(Continuation<? super C00971> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.importBookPreview(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {320, 342}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "refreshLocalBook", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$refreshLocalBook$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$refreshLocalBook$1.class */
    static final class C00981 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C00981(Continuation<? super C00981> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.refreshLocalBook(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {1815, 1826}, i = {0, 0, 0, 1, 1}, s = {"L$0", "L$1", "L$2", "L$2", "L$3"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData", "userNameSpace", "bookJsonArray"}, m = "removeBookGroupMulti", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$removeBookGroupMulti$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$removeBookGroupMulti$1.class */
    static final class C01001 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        long J$0;
        int I$0;
        int I$1;
        /* synthetic */ Object result;
        int label;

        C01001(Continuation<? super C01001> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.removeBookGroupMulti(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {3748}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "restoreFromMongodb", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$restoreFromMongodb$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$restoreFromMongodb$1.class */
    static final class C01021 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01021(Continuation<? super C01021> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.restoreFromMongodb(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {1386, 1399, 1400, 1404, 1407}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "saveBook", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$saveBook$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$saveBook$1.class */
    static final class C01031 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        /* synthetic */ Object result;
        int label;

        C01031(Continuation<? super C01031> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.saveBook(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {1715, 1742}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "saveBookConfig", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$saveBookConfig$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$saveBookConfig$1.class */
    static final class C01041 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01041(Continuation<? super C01041> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.saveBookConfig(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {665}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "saveBookContent", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$saveBookContent$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$saveBookContent$1.class */
    static final class C01051 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01051(Continuation<? super C01051> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.saveBookContent(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {1610}, i = {}, s = {}, n = {}, m = "saveBookCover", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$saveBookCover$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$saveBookCover$1.class */
    static final class C01061 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01061(Continuation<? super C01061> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.saveBookCover(null, null, null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {1753, 1780}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "saveBookGroupId", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$saveBookGroupId$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$saveBookGroupId$1.class */
    static final class C01071 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01071(Continuation<? super C01071> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.saveBookGroupId(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {414, 442, 448, 450}, i = {0, 0, 0, 2}, s = {"L$0", "L$1", "L$2", "L$4"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData", "chapterInfo"}, m = "saveBookProgress", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$saveBookProgress$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$saveBookProgress$1.class */
    static final class C01091 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int I$0;
        /* synthetic */ Object result;
        int label;

        C01091(Continuation<? super C01091> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.saveBookProgress(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {1583}, i = {}, s = {}, n = {}, m = "saveLocalBookCover", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$saveLocalBookCover$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$saveLocalBookCover$1.class */
    static final class C01101 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01101(Continuation<? super C01101> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.saveLocalBookCover(null, null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {2206, 2207}, i = {0, 0, 0, 0, 0, 1}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0"}, n = {"this", "book", "bookChapterList", "userNameSpace", "mutex", "mutex"}, m = "saveShelfBookLatestChapter", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$saveShelfBookLatestChapter$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$saveShelfBookLatestChapter$1.class */
    static final class C01111 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        /* synthetic */ Object result;
        int label;

        C01111(Continuation<? super C01111> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.saveShelfBookLatestChapter(null, null, null, null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {2528, 2539}, i = {0, 0, 0, 0}, s = {"L$0", "L$1", "L$2", "L$3"}, n = {"this", "userNameSpace", "userHome", "legadoHome"}, m = "saveToWebdav", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$saveToWebdav$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$saveToWebdav$1.class */
    static final class C01141 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        /* synthetic */ Object result;
        int label;

        C01141(Continuation<? super C01141> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.saveToWebdav(null, null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {752, 753, 773}, i = {0, 0, 0, 1, 1, 1}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData", "this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "searchBook", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$searchBook$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$searchBook$1.class */
    static final class C01151 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01151(Continuation<? super C01151> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.searchBook(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {3551, 3585, 3591, 3610}, i = {0, 0, 0, 3}, s = {"L$0", "L$1", "L$2", "L$6"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData", "resultList"}, m = "searchBookContent", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$searchBookContent$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$searchBookContent$1.class */
    static final class C01161 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        /* synthetic */ Object result;
        int label;

        C01161(Continuation<? super C01161> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.searchBookContent(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {779, 840}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "searchBookMulti", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$searchBookMulti$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$searchBookMulti$1.class */
    static final class C01171 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01171(Continuation<? super C01171> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.searchBookMulti(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {887, 959}, i = {0, 0, 0, 0, 1}, s = {"L$0", "L$1", "L$2", "L$3", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData", "response", "maxSize"}, m = "searchBookMultiSSE", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$searchBookMultiSSE$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$searchBookMultiSSE$1.class */
    static final class C01201 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        /* synthetic */ Object result;
        int label;

        C01201(Continuation<? super C01201> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.searchBookMultiSSE(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {1007, 1071}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "searchBookSource", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$searchBookSource$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$searchBookSource$1.class */
    static final class C01231 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        /* synthetic */ Object result;
        int label;

        C01231(Continuation<? super C01231> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.searchBookSource(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {1113, 1196}, i = {0, 0, 0, 0, 1}, s = {"L$0", "L$1", "L$2", "L$3", "L$6"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData", "response", "maxSize"}, m = "searchBookSourceSSE", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$searchBookSourceSSE$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$searchBookSourceSSE$1.class */
    static final class C01261 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        /* synthetic */ Object result;
        int label;

        C01261(Continuation<? super C01261> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.searchBookSourceSSE(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {1247}, i = {}, s = {}, n = {}, m = "searchBookWithSource", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$searchBookWithSource$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$searchBookWithSource$1.class */
    static final class C01291 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        C01291(Continuation<? super C01291> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.searchBookWithSource(null, null, false, null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {3639}, i = {0, 0}, s = {"L$3", "L$4"}, n = {"searchResultsWithinChapter", "chapterContent"}, m = "searchChapter", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$searchChapter$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$searchChapter$1.class */
    static final class C01311 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        /* synthetic */ Object result;
        int label;

        C01311(Continuation<? super C01311> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.searchChapter(null, null, null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {1624, 1684, 1687, 1706}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "returnData"}, m = "setBookSource", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$setBookSource$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$setBookSource$1.class */
    static final class C01321 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        /* synthetic */ Object result;
        int label;

        C01321(Continuation<? super C01321> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.setBookSource(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {3436}, i = {}, s = {}, n = {}, m = "setCover", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$setCover$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$setCover$1.class */
    static final class C01341 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        C01341(Continuation<? super C01341> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.setCover(null, null, null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {3462}, i = {0, 0, 0, 0, 0}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"}, n = {"this", "contentModel", "book", "epubBook", "userNameSpace"}, m = "setEpubContent", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$setEpubContent$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$setEpubContent$1.class */
    static final class C01351 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        /* synthetic */ Object result;
        int label;

        C01351(Continuation<? super C01351> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.setEpubContent(null, null, null, null, null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {2512}, i = {}, s = {}, n = {}, m = "syncFromWebdav", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$syncFromWebdav$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$syncFromWebdav$1.class */
    static final class C01371 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int I$0;
        int I$1;
        /* synthetic */ Object result;
        int label;

        C01371(Continuation<? super C01371> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.syncFromWebdav(null, null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {2891}, i = {0, 0, 0}, s = {"L$0", "L$1", "L$2"}, n = {"this", CoreConstants.CONTEXT_SCOPE_VALUE, "response"}, m = "textToSpeech", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$textToSpeech$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$textToSpeech$1.class */
    static final class C01381 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01381(Continuation<? super C01381> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.textToSpeech(null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {3018}, i = {}, s = {}, n = {}, m = "ttsByApi", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$ttsByApi$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$ttsByApi$1.class */
    static final class C01401 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C01401(Continuation<? super C01401> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.ttsByApi(null, null, null, null, this);
        }
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "BookController.kt", l = {3124}, i = {0}, s = {"L$0"}, n = {"response"}, m = "ttsByTextToSpeechCn", c = "com.htmake.reader.api.controller.BookController")
    /* renamed from: com.htmake.reader.api.controller.BookController$ttsByTextToSpeechCn$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$ttsByTextToSpeechCn$1.class */
    static final class C01411 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        C01411(Continuation<? super C01411> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return BookController.this.ttsByTextToSpeechCn(null, null, null, this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BookController(@NotNull CoroutineContext coroutineContext) {
        super(coroutineContext);
        Intrinsics.checkNotNullParameter(coroutineContext, "coroutineContext");
        this.bookInfoCache = ACache.Companion.get("bookInfoCache", 2000000L, 10000);
        this.concurrentLoopCount = 8;
        this.backupFileNames$delegate = LazyKt.lazy(new Function0<String[]>() { // from class: com.htmake.reader.api.controller.BookController$backupFileNames$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final String[] invoke() {
                return new String[]{"bookSource.json", "bookshelf.json", "bookGroup.json", "rssSources.json", "replaceRule.json", "bookmark.json", "userConfig.json", "httpTTS.json", "remoteBookSourceSub.json", DefaultData.txtTocRuleFileName};
            }
        });
        Object bean = SpringContextUtils.getBean("webClient", WebClient.class);
        Intrinsics.checkNotNullExpressionValue(bean, "getBean(\"webClient\", WebClient::class.java)");
        this.webClient = (WebClient) bean;
    }

    private final String[] getBackupFileNames() {
        return (String[]) this.backupFileNames$delegate.getValue();
    }

    private final ACache getInvalidBookSourceCache(String userNameSpace) {
        File cacheDir = new File(ExtKt.getWorkDir("storage", "cache", "invalidBookSourceCache", userNameSpace));
        ACache invalidBookSourceCache = ACache.Companion.get(cacheDir, 5000000L, DurationKt.NANOS_IN_MILLIS);
        return invalidBookSourceCache;
    }

    private final boolean isInvalidBookSource(BookSource bookSource, String userNameSpace) {
        return getInvalidBookSourceCache(userNameSpace).getAsString(bookSource.getBookSourceUrl()) != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addInvalidBookSource(String sourceUrl, Map<String, ? extends Object> invalidInfo, String userNameSpace) {
        getInvalidBookSourceCache(userNameSpace).put(sourceUrl, ExtKt.jsonEncode$default(invalidInfo, false, 2, null), OS2WindowsMetricsTable.WEIGHT_CLASS_SEMI_BOLD);
    }

    private final ACache getBookChaptersCache(String userNameSpace) {
        File cacheDir = new File(ExtKt.getWorkDir("storage", "cache", "bookChaptersCache", userNameSpace));
        ACache bookChaptersCache = ACache.Companion.get(cacheDir, 5000000L, DurationKt.NANOS_IN_MILLIS);
        return bookChaptersCache;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getInvalidBookSources(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 397
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.getInvalidBookSources(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0317  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0331  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x03ca  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0431  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getBookInfo(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1124
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.getBookInfo(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object getBookCover(@NotNull RoutingContext context, @NotNull Continuation<? super Unit> $completion) throws NoSuchAlgorithmException {
        List<String> listQueryParam = context.queryParam("path");
        Intrinsics.checkNotNullExpressionValue(listQueryParam, "context.queryParam(\"path\")");
        String str = (String) CollectionsKt.firstOrNull((List) listQueryParam);
        String coverUrl = str == null ? "" : str;
        if (coverUrl.length() == 0) {
            context.response().setStatusCode(404).end();
            return Unit.INSTANCE;
        }
        String ext = getFileExt(coverUrl, ImgUtil.IMAGE_TYPE_PNG);
        String md5Encode = MD5Utils.INSTANCE.md5Encode(coverUrl);
        String cachePath = ExtKt.getWorkDir("storage", "cache", "bookCoverCache", md5Encode + '.' + ext);
        File cacheFile = new File(cachePath);
        if (cacheFile.exists()) {
            BookControllerKt.logger.info("send cache: {}", cacheFile);
            HttpServerResponse httpServerResponseSendFile = context.response().putHeader("Cache-Control", "86400").sendFile(cacheFile.toString());
            return httpServerResponseSendFile == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? httpServerResponseSendFile : Unit.INSTANCE;
        }
        if (!cacheFile.getParentFile().exists()) {
            cacheFile.getParentFile().mkdirs();
        }
        CoroutineExceptionHandler exceptionHandler = new BookController$getBookCover$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.Key, context);
        Job jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(this, new MDCContext(null, 1, null).plus(Dispatchers.getIO()).plus(exceptionHandler), null, new C00822(context, cacheFile, this, coverUrl, null), 2, null);
        return jobLaunch$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? jobLaunch$default : Unit.INSTANCE;
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "BookController.kt", l = {197}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.controller.BookController$getBookCover$2")
    /* renamed from: com.htmake.reader.api.controller.BookController$getBookCover$2, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$getBookCover$2.class */
    static final class C00822 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ RoutingContext $context;
        final /* synthetic */ File $cacheFile;
        final /* synthetic */ BookController this$0;
        final /* synthetic */ String $coverUrl;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00822(RoutingContext $context, File $cacheFile, BookController this$0, String $coverUrl, Continuation<? super C00822> $completion) {
            super(2, $completion);
            this.$context = $context;
            this.$cacheFile = $cacheFile;
            this.this$0 = this$0;
            this.$coverUrl = $coverUrl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            return new C00822(this.$context, this.$cacheFile, this.this$0, this.$coverUrl, $completion);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
            return ((C00822) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object objAwaitResult;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    final BookController bookController = this.this$0;
                    final String str = this.$coverUrl;
                    this.label = 1;
                    objAwaitResult = VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<HttpResponse<Buffer>>>, Unit>() { // from class: com.htmake.reader.api.controller.BookController$getBookCover$2$result$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<HttpResponse<Buffer>>> handler) {
                            invoke2(handler);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(@NotNull Handler<AsyncResult<HttpResponse<Buffer>>> handler) {
                            Intrinsics.checkNotNullParameter(handler, "handler");
                            bookController.webClient.getAbs(str).timeout(3000L).send(handler);
                        }
                    }, this);
                    if (objAwaitResult == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    objAwaitResult = $result;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            HttpResponse result = (HttpResponse) objAwaitResult;
            Buffer bufferBodyAsBuffer = result.bodyAsBuffer();
            byte[] bodyBytes = bufferBodyAsBuffer == null ? null : bufferBodyAsBuffer.getBytes();
            if (bodyBytes != null) {
                HttpServerResponse res = this.$context.response().putHeader("Cache-Control", "86400");
                FilesKt.writeBytes(this.$cacheFile, bodyBytes);
                res.sendFile(this.$cacheFile.toString());
            } else {
                this.$context.response().setStatusCode(404).end();
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object importBookPreview(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r9) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 1133
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.importBookPreview(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getTxtTocRules(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 436
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.getTxtTocRules(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getChapterListByRule(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r8) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 380
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.getChapterListByRule(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object refreshLocalBook(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 472
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.refreshLocalBook(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x0614  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x065a  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0697  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x069d  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x06a4  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x06a8  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x06e9  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0395  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x039f  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x03a7  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x03af  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x049c  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0536  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getChapterList(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1814
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.getChapterList(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x02f7  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x03b7  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object saveBookProgress(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r15) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 993
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.saveBookProgress(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x063b  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x063f  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x065c  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0660  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0664  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x066c  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0807  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0880  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0886  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x08ed  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x096b  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0aa5  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0b21  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0b3d  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0b45  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x11b0  */
    /* JADX WARN: Removed duplicated region for block: B:332:0x11b6  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x11c8  */
    /* JADX WARN: Removed duplicated region for block: B:336:0x11cc  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x11d8 A[Catch: Exception -> 0x12a8, TryCatch #0 {Exception -> 0x12a8, blocks: (B:314:0x10c4, B:318:0x10da, B:325:0x117f, B:329:0x1193, B:333:0x11b8, B:337:0x11ce, B:339:0x11d8, B:341:0x1202, B:348:0x1298, B:324:0x1177, B:347:0x1290), top: B:370:0x0043 }] */
    /* JADX WARN: Removed duplicated region for block: B:340:0x1200  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x1249  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x04a0  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x04a4  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x04a8  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getBookContent(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r36, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r37) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 4947
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.getBookContent(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object saveBookContent(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 580
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.saveBookContent(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final String updateImageLinkInContent(Book book, BookChapter chapter, String content) {
        StringBuilder data = new StringBuilder("");
        String dataDir = ExtKt.getWorkDir("storage", "data");
        Iterable $this$forEach$iv = StringsKt.split$default((CharSequence) content, new String[]{"\n"}, false, 0, 6, (Object) null);
        for (Object element$iv : $this$forEach$iv) {
            String text = (String) element$iv;
            String strReplace$default = text;
            Matcher matcher = AppPattern.INSTANCE.getImgPattern().matcher(text);
            while (matcher.find()) {
                String it = matcher.group(1);
                if (it != null && StringsKt.indexOf$default((CharSequence) it, "__API_ROOT__", 0, false, 6, (Object) null) < 0) {
                    String src = NetworkUtils.INSTANCE.getAbsoluteURL(chapter.getUrl(), it);
                    File imageFile = BookHelp.INSTANCE.getImage(book, src);
                    if (imageFile.exists()) {
                        String path = imageFile.getPath();
                        Intrinsics.checkNotNullExpressionValue(path, "imageFile.path");
                        String imageUrl = Intrinsics.stringPlus("__API_ROOT__", StringsKt.replace$default(path, dataDir, "/book-assets", false, 4, (Object) null));
                        strReplace$default = StringsKt.replace$default(strReplace$default, it, imageUrl + "\" data-error=\"" + it, false, 4, (Object) null);
                    }
                }
            }
            data.append(strReplace$default).append("\n");
        }
        String string = data.toString();
        Intrinsics.checkNotNullExpressionValue(string, "data.toString()");
        return string;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object exploreBook(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r12) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 624
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.exploreBook(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object searchBook(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r12) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 669
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.searchBook(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Type inference failed for: r1v101, types: [T, java.io.File] */
    /* JADX WARN: Type inference failed for: r1v111, types: [T, java.io.File] */
    /* JADX WARN: Type inference failed for: r1v124, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v64, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v84, types: [T, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v88, types: [T, java.util.Map] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object searchBookMulti(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r44, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r45) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1491
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.searchBookMulti(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* renamed from: searchBookMulti$lambda-5, reason: not valid java name */
    private static final void m831searchBookMulti$lambda5(Ref.BooleanRef isEnd, BookController this$0, Void it) {
        Intrinsics.checkNotNullParameter(isEnd, "$isEnd");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BookControllerKt.logger.info("客户端已断开链接，停止 searchBookMulti");
        isEnd.element = true;
        JobKt__JobKt.cancel$default(this$0.getCoroutineContext(), (CancellationException) null, 1, (Object) null);
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\u0010\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", ""})
    @DebugMetadata(f = "BookController.kt", l = {853}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.controller.BookController$searchBookMulti$3")
    /* renamed from: com.htmake.reader.api.controller.BookController$searchBookMulti$3, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$searchBookMulti$3.class */
    static final class C01183 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ int I$0;
        final /* synthetic */ Ref.IntRef $maxSize;
        final /* synthetic */ Ref.IntRef $lastIndex;
        final /* synthetic */ Ref.ObjectRef<File> $bookSourceFile;
        final /* synthetic */ Ref.ObjectRef<String> $bookSourceGroup;
        final /* synthetic */ BookController this$0;
        final /* synthetic */ Book $book;
        final /* synthetic */ Ref.BooleanRef $accurate;
        final /* synthetic */ Ref.ObjectRef<String> $userNameSpace;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01183(Ref.IntRef $maxSize, Ref.IntRef $lastIndex, Ref.ObjectRef<File> $bookSourceFile, Ref.ObjectRef<String> $bookSourceGroup, BookController this$0, Book $book, Ref.BooleanRef $accurate, Ref.ObjectRef<String> $userNameSpace, Continuation<? super C01183> $completion) {
            super(3, $completion);
            this.$maxSize = $maxSize;
            this.$lastIndex = $lastIndex;
            this.$bookSourceFile = $bookSourceFile;
            this.$bookSourceGroup = $bookSourceGroup;
            this.this$0 = this$0;
            this.$book = $book;
            this.$accurate = $accurate;
            this.$userNameSpace = $userNameSpace;
        }

        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, int p2, @Nullable Continuation<Object> p3) {
            C01183 c01183 = new C01183(this.$maxSize, this.$lastIndex, this.$bookSourceFile, this.$bookSourceGroup, this.this$0, this.$book, this.$accurate, this.$userNameSpace, p3);
            c01183.I$0 = p2;
            return c01183.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Object> continuation) {
            return invoke(coroutineScope, num.intValue(), continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object objSearchBookWithSource;
            Function1<ObjectNode, Boolean> function1;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    int it = this.I$0;
                    if (it > this.$maxSize.element) {
                        return new ArrayList();
                    }
                    this.$lastIndex.element = Math.max(this.$lastIndex.element, it);
                    File file = this.$bookSourceFile.element;
                    if (this.$bookSourceGroup.element.length() == 0) {
                        function1 = null;
                    } else {
                        final Ref.ObjectRef<String> objectRef = this.$bookSourceGroup;
                        function1 = new Function1<ObjectNode, Boolean>() { // from class: com.htmake.reader.api.controller.BookController$searchBookMulti$3$bookSourceList$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Boolean invoke(ObjectNode objectNode) {
                                return Boolean.valueOf(invoke2(objectNode));
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final boolean invoke2(@NotNull ObjectNode it2) {
                                Intrinsics.checkNotNullParameter(it2, "it");
                                String _bookSourceGroup = it2.get("bookSourceGroup").asText();
                                String str = _bookSourceGroup;
                                return !(str == null || str.length() == 0) && StringsKt.indexOf$default((CharSequence) Intrinsics.stringPlus(_bookSourceGroup, ","), Intrinsics.stringPlus(objectRef.element, ","), 0, false, 6, (Object) null) >= 0;
                            }
                        };
                    }
                    JsonArray bookSourceList = ExtKt.parseJsonStringList$default(file, null, null, it, it, null, function1, 38, null);
                    if (bookSourceList == null || bookSourceList.isEmpty()) {
                        this.$maxSize.element = it;
                        return new ArrayList();
                    }
                    BookController bookController = this.this$0;
                    String string = bookSourceList.getString(0);
                    Intrinsics.checkNotNullExpressionValue(string, "bookSourceList.getString(0)");
                    this.label = 1;
                    objSearchBookWithSource = bookController.searchBookWithSource(string, this.$book, this.$accurate.element, this.$userNameSpace.element, this);
                    if (objSearchBookWithSource == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    objSearchBookWithSource = $result;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return (ArrayList) objSearchBookWithSource;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0710  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0714  */
    /* JADX WARN: Type inference failed for: r1v107, types: [T, java.io.File] */
    /* JADX WARN: Type inference failed for: r1v140, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v64, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v84, types: [T, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v97, types: [T, java.io.File] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object searchBookMultiSSE(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r44, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r45) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1861
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.searchBookMultiSSE(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* renamed from: searchBookMultiSSE$lambda-6, reason: not valid java name */
    private static final void m832searchBookMultiSSE$lambda6(Ref.BooleanRef isEnd, BookController this$0, Void it) {
        Intrinsics.checkNotNullParameter(isEnd, "$isEnd");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BookControllerKt.logger.info("客户端已断开链接，停止 searchBookMultiSSE");
        isEnd.element = true;
        JobKt__JobKt.cancel$default(this$0.getCoroutineContext(), (CancellationException) null, 1, (Object) null);
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\u0010\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", ""})
    @DebugMetadata(f = "BookController.kt", l = {972}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.controller.BookController$searchBookMultiSSE$3")
    /* renamed from: com.htmake.reader.api.controller.BookController$searchBookMultiSSE$3, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$searchBookMultiSSE$3.class */
    static final class C01213 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ int I$0;
        final /* synthetic */ Ref.IntRef $maxSize;
        final /* synthetic */ Ref.IntRef $lastIndex;
        final /* synthetic */ Ref.ObjectRef<File> $bookSourceFile;
        final /* synthetic */ Ref.ObjectRef<String> $bookSourceGroup;
        final /* synthetic */ BookController this$0;
        final /* synthetic */ Book $book;
        final /* synthetic */ Ref.BooleanRef $accurate;
        final /* synthetic */ Ref.ObjectRef<String> $userNameSpace;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01213(Ref.IntRef $maxSize, Ref.IntRef $lastIndex, Ref.ObjectRef<File> $bookSourceFile, Ref.ObjectRef<String> $bookSourceGroup, BookController this$0, Book $book, Ref.BooleanRef $accurate, Ref.ObjectRef<String> $userNameSpace, Continuation<? super C01213> $completion) {
            super(3, $completion);
            this.$maxSize = $maxSize;
            this.$lastIndex = $lastIndex;
            this.$bookSourceFile = $bookSourceFile;
            this.$bookSourceGroup = $bookSourceGroup;
            this.this$0 = this$0;
            this.$book = $book;
            this.$accurate = $accurate;
            this.$userNameSpace = $userNameSpace;
        }

        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, int p2, @Nullable Continuation<Object> p3) {
            C01213 c01213 = new C01213(this.$maxSize, this.$lastIndex, this.$bookSourceFile, this.$bookSourceGroup, this.this$0, this.$book, this.$accurate, this.$userNameSpace, p3);
            c01213.I$0 = p2;
            return c01213.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Object> continuation) {
            return invoke(coroutineScope, num.intValue(), continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object objSearchBookWithSource;
            Function1<ObjectNode, Boolean> function1;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    int it = this.I$0;
                    if (it > this.$maxSize.element) {
                        return new ArrayList();
                    }
                    this.$lastIndex.element = Math.max(this.$lastIndex.element, it);
                    File file = this.$bookSourceFile.element;
                    if (this.$bookSourceGroup.element.length() == 0) {
                        function1 = null;
                    } else {
                        final Ref.ObjectRef<String> objectRef = this.$bookSourceGroup;
                        function1 = new Function1<ObjectNode, Boolean>() { // from class: com.htmake.reader.api.controller.BookController$searchBookMultiSSE$3$bookSourceList$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Boolean invoke(ObjectNode objectNode) {
                                return Boolean.valueOf(invoke2(objectNode));
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final boolean invoke2(@NotNull ObjectNode it2) {
                                Intrinsics.checkNotNullParameter(it2, "it");
                                String _bookSourceGroup = it2.get("bookSourceGroup").asText();
                                String str = _bookSourceGroup;
                                return !(str == null || str.length() == 0) && StringsKt.indexOf$default((CharSequence) Intrinsics.stringPlus(_bookSourceGroup, ","), Intrinsics.stringPlus(objectRef.element, ","), 0, false, 6, (Object) null) >= 0;
                            }
                        };
                    }
                    JsonArray bookSourceList = ExtKt.parseJsonStringList$default(file, null, null, it, it, null, function1, 38, null);
                    if (bookSourceList == null || bookSourceList.isEmpty()) {
                        this.$maxSize.element = it;
                        return new ArrayList();
                    }
                    BookController bookController = this.this$0;
                    String string = bookSourceList.getString(0);
                    Intrinsics.checkNotNullExpressionValue(string, "bookSourceList.getString(0)");
                    this.label = 1;
                    objSearchBookWithSource = bookController.searchBookWithSource(string, this.$book, this.$accurate.element, this.$userNameSpace.element, this);
                    if (objSearchBookWithSource == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    objSearchBookWithSource = $result;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return (ArrayList) objSearchBookWithSource;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Type inference failed for: r1v109, types: [T, java.io.File] */
    /* JADX WARN: Type inference failed for: r1v158, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v65, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v75, types: [T, io.legado.app.data.entities.Book] */
    /* JADX WARN: Type inference failed for: r1v86, types: [T, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v99, types: [T, java.io.File] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object searchBookSource(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r16, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r17) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1429
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.searchBookSource(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* renamed from: searchBookSource$lambda-7, reason: not valid java name */
    private static final void m833searchBookSource$lambda7(Ref.BooleanRef isEnd, BookController this$0, Void it) {
        Intrinsics.checkNotNullParameter(isEnd, "$isEnd");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BookControllerKt.logger.info("客户端已断开链接，停止 searchBookSource");
        isEnd.element = true;
        JobKt__JobKt.cancel$default(this$0.getCoroutineContext(), (CancellationException) null, 1, (Object) null);
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\u0010\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", ""})
    @DebugMetadata(f = "BookController.kt", l = {1084}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.controller.BookController$searchBookSource$3")
    /* renamed from: com.htmake.reader.api.controller.BookController$searchBookSource$3, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$searchBookSource$3.class */
    static final class C01243 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ int I$0;
        final /* synthetic */ Ref.IntRef $maxSize;
        final /* synthetic */ Ref.IntRef $lastIndex;
        final /* synthetic */ Ref.ObjectRef<File> $bookSourceFile;
        final /* synthetic */ Ref.ObjectRef<String> $bookSourceGroup;
        final /* synthetic */ BookController this$0;
        final /* synthetic */ Ref.ObjectRef<Book> $book;
        final /* synthetic */ Ref.ObjectRef<String> $userNameSpace;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01243(Ref.IntRef $maxSize, Ref.IntRef $lastIndex, Ref.ObjectRef<File> $bookSourceFile, Ref.ObjectRef<String> $bookSourceGroup, BookController this$0, Ref.ObjectRef<Book> $book, Ref.ObjectRef<String> $userNameSpace, Continuation<? super C01243> $completion) {
            super(3, $completion);
            this.$maxSize = $maxSize;
            this.$lastIndex = $lastIndex;
            this.$bookSourceFile = $bookSourceFile;
            this.$bookSourceGroup = $bookSourceGroup;
            this.this$0 = this$0;
            this.$book = $book;
            this.$userNameSpace = $userNameSpace;
        }

        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, int p2, @Nullable Continuation<Object> p3) {
            C01243 c01243 = new C01243(this.$maxSize, this.$lastIndex, this.$bookSourceFile, this.$bookSourceGroup, this.this$0, this.$book, this.$userNameSpace, p3);
            c01243.I$0 = p2;
            return c01243.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Object> continuation) {
            return invoke(coroutineScope, num.intValue(), continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object objSearchBookWithSource$default;
            Function1<ObjectNode, Boolean> function1;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    int it = this.I$0;
                    if (it > this.$maxSize.element) {
                        return new ArrayList();
                    }
                    this.$lastIndex.element = Math.max(this.$lastIndex.element, it);
                    File file = this.$bookSourceFile.element;
                    if (this.$bookSourceGroup.element.length() == 0) {
                        function1 = null;
                    } else {
                        final Ref.ObjectRef<String> objectRef = this.$bookSourceGroup;
                        function1 = new Function1<ObjectNode, Boolean>() { // from class: com.htmake.reader.api.controller.BookController$searchBookSource$3$bookSourceList$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Boolean invoke(ObjectNode objectNode) {
                                return Boolean.valueOf(invoke2(objectNode));
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final boolean invoke2(@NotNull ObjectNode it2) {
                                Intrinsics.checkNotNullParameter(it2, "it");
                                String _bookSourceGroup = it2.get("bookSourceGroup").asText();
                                String str = _bookSourceGroup;
                                return !(str == null || str.length() == 0) && StringsKt.indexOf$default((CharSequence) Intrinsics.stringPlus(_bookSourceGroup, ","), Intrinsics.stringPlus(objectRef.element, ","), 0, false, 6, (Object) null) >= 0;
                            }
                        };
                    }
                    JsonArray bookSourceList = ExtKt.parseJsonStringList$default(file, null, null, it, it, null, function1, 38, null);
                    if (bookSourceList == null || bookSourceList.isEmpty()) {
                        this.$maxSize.element = it;
                        return new ArrayList();
                    }
                    BookController bookController = this.this$0;
                    String string = bookSourceList.getString(0);
                    Intrinsics.checkNotNullExpressionValue(string, "bookSourceList.getString(0)");
                    this.label = 1;
                    objSearchBookWithSource$default = BookController.searchBookWithSource$default(bookController, string, this.$book.element, false, this.$userNameSpace.element, this, 4, null);
                    if (objSearchBookWithSource$default == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    objSearchBookWithSource$default = $result;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return (ArrayList) objSearchBookWithSource$default;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x078c  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0788  */
    /* JADX WARN: Type inference failed for: r1v111, types: [T, java.io.File] */
    /* JADX WARN: Type inference failed for: r1v180, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v67, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v74, types: [T, io.legado.app.data.entities.Book] */
    /* JADX WARN: Type inference failed for: r1v84, types: [T, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v98, types: [T, java.io.File] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object searchBookSourceSSE(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r16, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r17) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1981
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.searchBookSourceSSE(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* renamed from: searchBookSourceSSE$lambda-8, reason: not valid java name */
    private static final void m834searchBookSourceSSE$lambda8(Ref.BooleanRef isEnd, BookController this$0, Void it) {
        Intrinsics.checkNotNullParameter(isEnd, "$isEnd");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BookControllerKt.logger.info("客户端已断开链接，停止 searchBookSourceSSE");
        isEnd.element = true;
        JobKt__JobKt.cancel$default(this$0.getCoroutineContext(), (CancellationException) null, 1, (Object) null);
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\u0010\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", ""})
    @DebugMetadata(f = "BookController.kt", l = {1209}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.controller.BookController$searchBookSourceSSE$3")
    /* renamed from: com.htmake.reader.api.controller.BookController$searchBookSourceSSE$3, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$searchBookSourceSSE$3.class */
    static final class C01273 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ int I$0;
        final /* synthetic */ Ref.IntRef $maxSize;
        final /* synthetic */ Ref.IntRef $lastIndex;
        final /* synthetic */ Ref.ObjectRef<File> $bookSourceFile;
        final /* synthetic */ Ref.ObjectRef<String> $bookSourceGroup;
        final /* synthetic */ BookController this$0;
        final /* synthetic */ Ref.ObjectRef<Book> $book;
        final /* synthetic */ Ref.ObjectRef<String> $userNameSpace;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01273(Ref.IntRef $maxSize, Ref.IntRef $lastIndex, Ref.ObjectRef<File> $bookSourceFile, Ref.ObjectRef<String> $bookSourceGroup, BookController this$0, Ref.ObjectRef<Book> $book, Ref.ObjectRef<String> $userNameSpace, Continuation<? super C01273> $completion) {
            super(3, $completion);
            this.$maxSize = $maxSize;
            this.$lastIndex = $lastIndex;
            this.$bookSourceFile = $bookSourceFile;
            this.$bookSourceGroup = $bookSourceGroup;
            this.this$0 = this$0;
            this.$book = $book;
            this.$userNameSpace = $userNameSpace;
        }

        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, int p2, @Nullable Continuation<Object> p3) {
            C01273 c01273 = new C01273(this.$maxSize, this.$lastIndex, this.$bookSourceFile, this.$bookSourceGroup, this.this$0, this.$book, this.$userNameSpace, p3);
            c01273.I$0 = p2;
            return c01273.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Object> continuation) {
            return invoke(coroutineScope, num.intValue(), continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object objSearchBookWithSource$default;
            Function1<ObjectNode, Boolean> function1;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    int it = this.I$0;
                    if (it > this.$maxSize.element) {
                        return new ArrayList();
                    }
                    this.$lastIndex.element = Math.max(this.$lastIndex.element, it);
                    File file = this.$bookSourceFile.element;
                    if (this.$bookSourceGroup.element.length() == 0) {
                        function1 = null;
                    } else {
                        final Ref.ObjectRef<String> objectRef = this.$bookSourceGroup;
                        function1 = new Function1<ObjectNode, Boolean>() { // from class: com.htmake.reader.api.controller.BookController$searchBookSourceSSE$3$bookSourceList$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Boolean invoke(ObjectNode objectNode) {
                                return Boolean.valueOf(invoke2(objectNode));
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final boolean invoke2(@NotNull ObjectNode it2) {
                                Intrinsics.checkNotNullParameter(it2, "it");
                                String _bookSourceGroup = it2.get("bookSourceGroup").asText();
                                String str = _bookSourceGroup;
                                return !(str == null || str.length() == 0) && StringsKt.indexOf$default((CharSequence) Intrinsics.stringPlus(_bookSourceGroup, ","), Intrinsics.stringPlus(objectRef.element, ","), 0, false, 6, (Object) null) >= 0;
                            }
                        };
                    }
                    JsonArray bookSourceList = ExtKt.parseJsonStringList$default(file, null, null, it, it, null, function1, 38, null);
                    if (bookSourceList == null || bookSourceList.isEmpty()) {
                        this.$maxSize.element = it;
                        return new ArrayList();
                    }
                    BookController bookController = this.this$0;
                    String string = bookSourceList.getString(0);
                    Intrinsics.checkNotNullExpressionValue(string, "bookSourceList.getString(0)");
                    this.label = 1;
                    objSearchBookWithSource$default = BookController.searchBookWithSource$default(bookController, string, this.$book.element, false, this.$userNameSpace.element, this, 4, null);
                    if (objSearchBookWithSource$default == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    objSearchBookWithSource$default = $result;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return (ArrayList) objSearchBookWithSource$default;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /* JADX WARN: Type inference failed for: r1v4, types: [T, java.util.ArrayList] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object searchBookWithSource(@org.jetbrains.annotations.NotNull java.lang.String r12, @org.jetbrains.annotations.NotNull io.legado.app.data.entities.Book r13, boolean r14, @org.jetbrains.annotations.NotNull java.lang.String r15, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.ArrayList<io.legado.app.data.entities.SearchBook>> r16) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 294
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.searchBookWithSource(java.lang.String, io.legado.app.data.entities.Book, boolean, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object searchBookWithSource$default(BookController bookController, String str, Book book, boolean z, String str2, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            str2 = "default";
        }
        return bookController.searchBookWithSource(str, book, z, str2, continuation);
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "BookController.kt", l = {1251}, i = {0}, s = {"J$0"}, n = {"start"}, m = "invokeSuspend", c = "com.htmake.reader.api.controller.BookController$searchBookWithSource$2")
    /* renamed from: com.htmake.reader.api.controller.BookController$searchBookWithSource$2, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$searchBookWithSource$2.class */
    static final class C01302 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        long J$0;
        int label;
        final /* synthetic */ Ref.ObjectRef<BookSource> $bookSource;
        final /* synthetic */ String $userNameSpace;
        final /* synthetic */ Book $book;
        final /* synthetic */ boolean $accurate;
        final /* synthetic */ Ref.ObjectRef<ArrayList<SearchBook>> $resultList;
        final /* synthetic */ BookController this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01302(Ref.ObjectRef<BookSource> $bookSource, String $userNameSpace, Book $book, boolean $accurate, Ref.ObjectRef<ArrayList<SearchBook>> $resultList, BookController this$0, Continuation<? super C01302> $completion) {
            super(2, $completion);
            this.$bookSource = $bookSource;
            this.$userNameSpace = $userNameSpace;
            this.$book = $book;
            this.$accurate = $accurate;
            this.$resultList = $resultList;
            this.this$0 = this$0;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            return new C01302(this.$bookSource, this.$userNameSpace, this.$book, this.$accurate, this.$resultList, this.this$0, $completion);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
            return ((C01302) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:28:0x0116 A[Catch: Exception -> 0x0172, TryCatch #0 {Exception -> 0x0172, blocks: (B:5:0x0024, B:12:0x0072, B:14:0x0086, B:16:0x0099, B:18:0x00b5, B:20:0x00c7, B:25:0x00e8, B:27:0x00fa, B:28:0x0116, B:30:0x011d, B:32:0x0136, B:34:0x014f, B:11:0x006c), top: B:44:0x0009 }] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r10) throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 476
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.C01302.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Type inference failed for: r1v14, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v17, types: [T, io.legado.app.data.entities.Book] */
    /* JADX WARN: Type inference failed for: r1v21, types: [T, io.vertx.core.json.JsonArray] */
    /* JADX WARN: Type inference failed for: r1v26, types: [T, java.util.ArrayList] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getAvailableBookSource(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1018
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.getAvailableBookSource(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\u0010\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", ""})
    @DebugMetadata(f = "BookController.kt", l = {1321}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.controller.BookController$getAvailableBookSource$2")
    /* renamed from: com.htmake.reader.api.controller.BookController$getAvailableBookSource$2, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$getAvailableBookSource$2.class */
    static final class C00792 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Object>, Object> {
        int label;
        /* synthetic */ int I$0;
        final /* synthetic */ Ref.ObjectRef<JsonArray> $bookSourceList;
        final /* synthetic */ BookController this$0;
        final /* synthetic */ Ref.ObjectRef<String> $userNameSpace;
        final /* synthetic */ Ref.ObjectRef<Book> $book;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00792(Ref.ObjectRef<JsonArray> $bookSourceList, BookController this$0, Ref.ObjectRef<String> $userNameSpace, Ref.ObjectRef<Book> $book, Continuation<? super C00792> $completion) {
            super(3, $completion);
            this.$bookSourceList = $bookSourceList;
            this.this$0 = this$0;
            this.$userNameSpace = $userNameSpace;
            this.$book = $book;
        }

        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, int p2, @Nullable Continuation<Object> p3) {
            C00792 c00792 = new C00792(this.$bookSourceList, this.this$0, this.$userNameSpace, this.$book, p3);
            c00792.I$0 = p2;
            return c00792.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Object> continuation) {
            return invoke(coroutineScope, num.intValue(), continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Exception {
            Object objSearchBookWithSource$default;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    int it = this.I$0;
                    SearchBook searchBook = (SearchBook) this.$bookSourceList.element.getJsonObject(it).mapTo(SearchBook.class);
                    if (searchBook.getOrigin().equals(BookType.local)) {
                        return CollectionsKt.arrayListOf(searchBook);
                    }
                    String bookSource = this.this$0.getBookSourceStringBySourceURLOpt(searchBook.getOrigin(), this.$userNameSpace.element);
                    if (bookSource == null) {
                        return new ArrayList();
                    }
                    this.label = 1;
                    objSearchBookWithSource$default = BookController.searchBookWithSource$default(this.this$0, bookSource, this.$book.element, false, this.$userNameSpace.element, this, 4, null);
                    if (objSearchBookWithSource$default == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    objSearchBookWithSource$default = $result;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return (ArrayList) objSearchBookWithSource$default;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getBookshelf(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 429
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.getBookshelf(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getShelfBook(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 378
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.getShelfBook(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x037a  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0396  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object saveBook(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r11) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 942
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.saveBook(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final Pair<Book, String> saveBookToShelf(@NotNull Book _book, @NotNull String userNameSpace, @NotNull RoutingContext context) {
        User userInfo;
        Intrinsics.checkNotNullParameter(_book, "_book");
        Intrinsics.checkNotNullParameter(userNameSpace, "userNameSpace");
        Intrinsics.checkNotNullParameter(context, "context");
        String origin = _book.getOrigin();
        if (origin == null || origin.length() == 0) {
            return new Pair<>(_book, "未找到书源信息");
        }
        String bookUrl = _book.getBookUrl();
        if (bookUrl == null || bookUrl.length() == 0) {
            return new Pair<>(_book, "书籍链接不能为空");
        }
        JsonArray bookshelf = ExtKt.asJsonArray(getUserStorage(userNameSpace, "bookshelf"));
        if (bookshelf == null) {
            bookshelf = new JsonArray();
        }
        int existIndex = -1;
        int i = 0;
        int size = bookshelf.size();
        if (0 < size) {
            while (true) {
                int i2 = i;
                i++;
                String name = bookshelf.getJsonObject(i2).getString("name", "");
                String author = bookshelf.getJsonObject(i2).getString("author", "");
                if (!name.equals(_book.getName()) || !author.equals(_book.getAuthor())) {
                    if (i >= size) {
                        break;
                    }
                } else {
                    existIndex = i2;
                    break;
                }
            }
        }
        if (existIndex < 0 && (userInfo = (User) context.get("userInfo")) != null && bookshelf.size() >= userInfo.getBook_limit()) {
            return new Pair<>(_book, "你已达到书籍数上限，请联系管理员");
        }
        if (_book.isLocalBook()) {
            if (StringsKt.startsWith$default(_book.getBookUrl(), "/assets/", false, 2, (Object) null) || StringsKt.startsWith$default(_book.getBookUrl(), "assets/", false, 2, (Object) null)) {
                File tempFile = new File(ExtKt.getWorkDir(Intrinsics.stringPlus("storage", _book.getBookUrl())));
                if (!tempFile.exists()) {
                    return new Pair<>(_book, "上传书籍不存在");
                }
                String relativeLocalFilePath = Paths.get("storage", "data", userNameSpace, _book.getName() + '_' + _book.getAuthor(), tempFile.getName()).toString();
                String relativeLocalFileUrl = "storage/data/" + userNameSpace + '/' + _book.getName() + '_' + _book.getAuthor() + '/' + ((Object) tempFile.getName());
                String localFilePath = ExtKt.getWorkDir(relativeLocalFilePath);
                BookControllerKt.logger.info("localFilePath: {}", localFilePath);
                File localFile = new File(localFilePath);
                ExtKt.deleteRecursively(localFile);
                if (!localFile.getParentFile().exists()) {
                    localFile.getParentFile().mkdirs();
                }
                if (!FilesKt.copyRecursively$default(tempFile, localFile, false, null, 6, null)) {
                    return new Pair<>(_book, "导入本地书籍失败");
                }
                ExtKt.deleteRecursively(tempFile);
                _book.setBookUrl(relativeLocalFileUrl);
                _book.setOriginName(relativeLocalFilePath);
                if (_book.isEpub()) {
                    if (!extractEpub$default(this, _book, false, 2, null)) {
                        return new Pair<>(_book, "导入本地Epub书籍失败");
                    }
                } else if (_book.isCbz()) {
                    if (!extractCbz$default(this, _book, false, 2, null)) {
                        return new Pair<>(_book, "导入本地CBZ书籍失败");
                    }
                } else if (_book.isPdf() && !convertPdfToImage$default(this, _book, false, 2, null)) {
                    return new Pair<>(_book, "本地PDF书籍转换失败");
                }
            } else if (StringsKt.indexOf$default((CharSequence) _book.getBookUrl(), "localStore", 0, false, 6, (Object) null) >= 0) {
                File tempFile2 = new File(ExtKt.getWorkDir(_book.getBookUrl()));
                if (!tempFile2.exists()) {
                    return new Pair<>(_book, "本地书仓书籍不存在");
                }
                String relativeLocalFileUrl2 = "storage/data/" + userNameSpace + '/' + _book.getName() + '_' + _book.getAuthor() + '/' + ((Object) tempFile2.getName());
                _book.setBookUrl(relativeLocalFileUrl2);
                if (_book.isEpub()) {
                    if (!extractEpub$default(this, _book, false, 2, null)) {
                        return new Pair<>(_book, "导入本地Epub书籍失败");
                    }
                } else if (_book.isCbz()) {
                    if (!extractCbz$default(this, _book, false, 2, null)) {
                        return new Pair<>(_book, "导入本地CBZ书籍失败");
                    }
                } else if (_book.isPdf() && !convertPdfToImage$default(this, _book, false, 2, null)) {
                    return new Pair<>(_book, "本地PDF书籍转换失败");
                }
            } else if (StringsKt.indexOf$default((CharSequence) _book.getBookUrl(), "webdav", 0, false, 6, (Object) null) >= 0) {
                File tempFile3 = new File(ExtKt.getWorkDir(_book.getBookUrl()));
                if (!tempFile3.exists()) {
                    return new Pair<>(_book, "webdav书仓书籍不存在");
                }
                String relativeLocalFileUrl3 = "storage/data/" + userNameSpace + '/' + _book.getName() + '_' + _book.getAuthor() + '/' + ((Object) tempFile3.getName());
                _book.setBookUrl(relativeLocalFileUrl3);
                if (_book.isEpub()) {
                    if (!extractEpub$default(this, _book, false, 2, null)) {
                        return new Pair<>(_book, "导入本地Epub书籍失败");
                    }
                } else if (_book.isCbz()) {
                    if (!extractCbz$default(this, _book, false, 2, null)) {
                        return new Pair<>(_book, "导入本地CBZ书籍失败");
                    }
                } else if (_book.isPdf() && !convertPdfToImage$default(this, _book, false, 2, null)) {
                    return new Pair<>(_book, "本地PDF书籍转换失败");
                }
            }
        }
        _book.setInShelf(true);
        if (existIndex < 0) {
            bookshelf.add(JsonObject.mapFrom(_book));
        } else {
            List bookList = bookshelf.getList();
            Book existBook = (Book) bookshelf.getJsonObject(existIndex).mapTo(Book.class);
            _book.setDurChapterIndex(existBook.getDurChapterIndex());
            _book.setDurChapterTitle(existBook.getDurChapterTitle());
            _book.setDurChapterTime(existBook.getDurChapterTime());
            String displayCover = existBook.getDisplayCover();
            if (!(displayCover == null || displayCover.length() == 0)) {
                String displayCover2 = existBook.getDisplayCover();
                Intrinsics.checkNotNull(displayCover2);
                if (StringsKt.startsWith$default(displayCover2, "/", false, 2, (Object) null)) {
                    String displayCover3 = existBook.getDisplayCover();
                    Intrinsics.checkNotNull(displayCover3);
                    if (!displayCover3.equals(_book.getDisplayCover())) {
                        String displayCover4 = existBook.getDisplayCover();
                        Intrinsics.checkNotNull(displayCover4);
                        String cachePath = ExtKt.getWorkDir("storage", displayCover4);
                        FileUtils.INSTANCE.deleteFile(cachePath);
                    }
                }
            }
            bookList.set(existIndex, JsonObject.mapFrom(_book));
            bookshelf = new JsonArray(bookList);
        }
        List sourceList = CollectionsKt.listOf(_book.toSearchBook());
        saveBookSources$default(this, _book, sourceList, userNameSpace, false, 8, null);
        saveUserStorage(userNameSpace, "bookshelf", bookshelf);
        return new Pair<>(_book, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object saveLocalBookCover(io.legado.app.data.entities.Book r7, java.lang.String r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 435
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.saveLocalBookCover(io.legado.app.data.entities.Book, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object saveBookCover(@org.jetbrains.annotations.NotNull io.legado.app.data.entities.Book r17, @org.jetbrains.annotations.NotNull java.lang.String r18, @org.jetbrains.annotations.Nullable java.lang.String r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 517
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.saveBookCover(io.legado.app.data.entities.Book, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object saveBookCover$default(BookController bookController, Book book, String str, String str2, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = null;
        }
        return bookController.saveBookCover(book, str, str2, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0463  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x04b1  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x04b7  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x04f1  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object setBookSource(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r15) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 1325
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.setBookSource(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object saveBookConfig(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 627
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.saveBookConfig(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object saveBookGroupId(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 630
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.saveBookGroupId(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0128, code lost:
    
        if (0 < r18) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x012b, code lost:
    
        r0 = r17;
        r17 = r17 + 1;
        r0 = (io.legado.app.data.entities.Book) r16.getJsonObject(r0).mapTo(io.legado.app.data.entities.Book.class);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, "book");
        r5 = r13;
        r23.L$0 = r9;
        r23.L$1 = r12;
        r23.L$2 = r15;
        r23.L$3 = r16;
        r23.J$0 = r13;
        r23.I$0 = r17;
        r23.I$1 = r18;
        r23.label = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x019e, code lost:
    
        if (r9.editShelfBook(r0, r15, new com.htmake.reader.api.controller.BookController.AnonymousClass2(), r23) != r0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x01a3, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x01eb, code lost:
    
        if (r17 >= r18) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x01f8, code lost:
    
        return com.htmake.reader.api.ReturnData.setData$default(r12, "", null, 2, null);
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x01eb -> B:25:0x012b). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object addBookGroupMulti(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 516
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.addBookGroupMulti(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0128, code lost:
    
        if (0 < r18) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x012b, code lost:
    
        r0 = r17;
        r17 = r17 + 1;
        r0 = (io.legado.app.data.entities.Book) r16.getJsonObject(r0).mapTo(io.legado.app.data.entities.Book.class);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, "book");
        r5 = r13;
        r23.L$0 = r9;
        r23.L$1 = r12;
        r23.L$2 = r15;
        r23.L$3 = r16;
        r23.J$0 = r13;
        r23.I$0 = r17;
        r23.I$1 = r18;
        r23.label = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x019e, code lost:
    
        if (r9.editShelfBook(r0, r15, new com.htmake.reader.api.controller.BookController.C01012(), r23) != r0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x01a3, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x01eb, code lost:
    
        if (r17 >= r18) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x01f8, code lost:
    
        return com.htmake.reader.api.ReturnData.setData$default(r12, "", null, 2, null);
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x01eb -> B:25:0x012b). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object removeBookGroupMulti(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 516
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.removeBookGroupMulti(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object deleteBook(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 700
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.deleteBook(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object deleteBooks(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 693
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.deleteBooks(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object saveBookInfoCache(@NotNull List<Book> bookList, @NotNull Continuation<? super List<Book>> $completion) {
        if (bookList.size() > 0) {
            int i = 0;
            int size = bookList.size();
            if (0 < size) {
                do {
                    int i2 = i;
                    i++;
                    Book book = bookList.get(i2);
                    ACache aCache = this.bookInfoCache;
                    String bookUrl = book.getBookUrl();
                    Map<String, Object> map = JsonObject.mapFrom(book).getMap();
                    Intrinsics.checkNotNullExpressionValue(map, "mapFrom(book).map");
                    aCache.put(bookUrl, ExtKt.jsonEncode$default(map, false, 2, null));
                } while (i < size);
            }
        }
        return bookList;
    }

    @Nullable
    public final Object mergeBookCacheInfo(@NotNull Book book, @NotNull Continuation<? super Book> $completion) {
        Object map;
        String json;
        Book book2;
        String asString = this.bookInfoCache.getAsString(book.getBookUrl());
        if (asString == null || (map = ExtKt.toMap(asString)) == null) {
            book2 = null;
        } else {
            if (!(map instanceof String)) {
                json = ExtKt.getGson().toJson(map);
            } else {
                json = (String) map;
            }
            String json$iv$iv = json;
            book2 = (Book) ExtKt.getGson().fromJson(json$iv$iv, new TypeToken<Book>() { // from class: com.htmake.reader.api.controller.BookController$mergeBookCacheInfo$$inlined$toDataClass$1
            }.getType());
        }
        Book cacheInfo = book2;
        if (cacheInfo != null) {
            return ExtKt.fillData(book, cacheInfo, CollectionsKt.listOf((Object[]) new String[]{"name", "author", "coverUrl", "tocUrl", "intro", "latestChapterTitle", "wordCount"}));
        }
        return book;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Type inference failed for: r1v5, types: [T, io.vertx.core.json.JsonArray] */
    /* JADX WARN: Type inference failed for: r1v8, types: [T, java.util.ArrayList] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getBookShelfBooks(boolean r16, @org.jetbrains.annotations.NotNull java.lang.String r17, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<io.legado.app.data.entities.Book>> r18) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 325
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.getBookShelfBooks(boolean, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object getBookShelfBooks$default(BookController bookController, boolean z, String str, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return bookController.getBookShelfBooks(z, str, continuation);
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\u0010\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", ""})
    @DebugMetadata(f = "BookController.kt", l = {1961, 1970}, i = {0, 1}, s = {"L$0", "L$0"}, n = {"book", "book"}, m = "invokeSuspend", c = "com.htmake.reader.api.controller.BookController$getBookShelfBooks$2")
    /* renamed from: com.htmake.reader.api.controller.BookController$getBookShelfBooks$2, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$getBookShelfBooks$2.class */
    static final class C00852 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Object>, Object> {
        Object L$0;
        int label;
        /* synthetic */ int I$0;
        final /* synthetic */ Ref.ObjectRef<JsonArray> $bookshelf;
        final /* synthetic */ boolean $refresh;
        final /* synthetic */ BookController this$0;
        final /* synthetic */ String $userNameSpace;
        final /* synthetic */ Mutex $syncMutex;
        final /* synthetic */ Ref.ObjectRef<ArrayList<Book>> $bookList;
        final /* synthetic */ Mutex $mutex;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00852(Ref.ObjectRef<JsonArray> $bookshelf, boolean $refresh, BookController this$0, String $userNameSpace, Mutex $syncMutex, Ref.ObjectRef<ArrayList<Book>> $bookList, Mutex $mutex, Continuation<? super C00852> $completion) {
            super(3, $completion);
            this.$bookshelf = $bookshelf;
            this.$refresh = $refresh;
            this.this$0 = this$0;
            this.$userNameSpace = $userNameSpace;
            this.$syncMutex = $syncMutex;
            this.$bookList = $bookList;
            this.$mutex = $mutex;
        }

        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, int p2, @Nullable Continuation<Object> p3) {
            C00852 c00852 = new C00852(this.$bookshelf, this.$refresh, this.this$0, this.$userNameSpace, this.$syncMutex, this.$bookList, this.$mutex, p3);
            c00852.I$0 = p2;
            return c00852.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Object> continuation) {
            return invoke(coroutineScope, num.intValue(), continuation);
        }

        /*  JADX ERROR: JadxRuntimeException in pass: BlockSplitter
            jadx.core.utils.exceptions.JadxRuntimeException: Unexpected missing predecessor for block: B:10:0x0078
            	at jadx.core.dex.visitors.blocks.BlockSplitter.addTempConnectionsForExcHandlers(BlockSplitter.java:280)
            	at jadx.core.dex.visitors.blocks.BlockSplitter.visit(BlockSplitter.java:79)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r12) {
            /*
                Method dump skipped, instructions count: 371
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.C00852.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: BookController.kt */
        @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010��\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lio/legado/app/data/entities/BookChapter;", "Lkotlinx/coroutines/CoroutineScope;"})
        @DebugMetadata(f = "BookController.kt", l = {1962}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.controller.BookController$getBookShelfBooks$2$1")
        /* renamed from: com.htmake.reader.api.controller.BookController$getBookShelfBooks$2$1, reason: invalid class name */
        /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$getBookShelfBooks$2$1.class */
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends BookChapter>>, Object> {
            int label;
            final /* synthetic */ BookController this$0;
            final /* synthetic */ Ref.ObjectRef<Book> $book;
            final /* synthetic */ Ref.ObjectRef<String> $bookSource;
            final /* synthetic */ boolean $refresh;
            final /* synthetic */ String $userNameSpace;
            final /* synthetic */ Mutex $mutex;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(BookController this$0, Ref.ObjectRef<Book> $book, Ref.ObjectRef<String> $bookSource, boolean $refresh, String $userNameSpace, Mutex $mutex, Continuation<? super AnonymousClass1> $completion) {
                super(2, $completion);
                this.this$0 = this$0;
                this.$book = $book;
                this.$bookSource = $bookSource;
                this.$refresh = $refresh;
                this.$userNameSpace = $userNameSpace;
                this.$mutex = $mutex;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @NotNull
            public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
                return new AnonymousClass1(this.this$0, this.$book, this.$bookSource, this.$refresh, this.$userNameSpace, this.$mutex, $completion);
            }

            @Nullable
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(@NotNull CoroutineScope p1, @Nullable Continuation<? super List<BookChapter>> p2) {
                return ((AnonymousClass1) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends BookChapter>> continuation) {
                return invoke2(coroutineScope, (Continuation<? super List<BookChapter>>) continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) throws Exception {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        BookController bookController = this.this$0;
                        Book book = this.$book.element;
                        Intrinsics.checkNotNullExpressionValue(book, "book");
                        this.label = 1;
                        Object localChapterList = bookController.getLocalChapterList(book, this.$bookSource.element, this.$refresh, this.$userNameSpace, false, this.$mutex, this);
                        if (localChapterList == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return localChapterList;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        return $result;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0548  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0576  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x05b3  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0629  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x030a  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0353  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getLocalChapterList(@org.jetbrains.annotations.NotNull io.legado.app.data.entities.Book r10, @org.jetbrains.annotations.Nullable java.lang.String r11, boolean r12, @org.jetbrains.annotations.NotNull java.lang.String r13, boolean r14, @org.jetbrains.annotations.Nullable kotlinx.coroutines.sync.Mutex r15, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<io.legado.app.data.entities.BookChapter>> r16) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 1684
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.getLocalChapterList(io.legado.app.data.entities.Book, java.lang.String, boolean, java.lang.String, boolean, kotlinx.coroutines.sync.Mutex, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object getLocalChapterList$default(BookController bookController, Book book, String str, boolean z, String str2, boolean z2, Mutex mutex, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 16) != 0) {
            z2 = true;
        }
        if ((i & 32) != 0) {
            mutex = null;
        }
        return bookController.getLocalChapterList(book, str, z, str2, z2, mutex, continuation);
    }

    public static /* synthetic */ Object getBookSourceString$default(BookController bookController, RoutingContext routingContext, String str, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "";
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return bookController.getBookSourceString(routingContext, str, z, continuation);
    }

    @Nullable
    public final Object getBookSourceString(@NotNull RoutingContext context, @NotNull String sourceUrl, boolean withExploreUrl, @NotNull Continuation<? super String> $completion) throws Exception {
        String bookSourceUrl;
        JsonObject bookSource;
        String bookSourceString = null;
        if (context.request().method() == HttpMethod.POST && (bookSource = context.getBodyAsJson().getJsonObject("bookSource")) != null) {
            bookSourceString = bookSource.toString();
        }
        String userNameSpace = getUserNameSpace(context);
        String str = bookSourceString;
        if (str == null || str.length() == 0) {
            if (context.request().method() == HttpMethod.POST) {
                String string = context.getBodyAsJson().getString("bookSourceUrl", "");
                Intrinsics.checkNotNullExpressionValue(string, "context.bodyAsJson.getString(\"bookSourceUrl\", \"\")");
                bookSourceUrl = string;
            } else {
                List<String> listQueryParam = context.queryParam("bookSourceUrl");
                Intrinsics.checkNotNullExpressionValue(listQueryParam, "context.queryParam(\"bookSourceUrl\")");
                String str2 = (String) CollectionsKt.firstOrNull((List) listQueryParam);
                bookSourceUrl = str2 == null ? "" : str2;
            }
            if (!StringsKt.isBlank(bookSourceUrl)) {
                bookSourceString = getBookSourceStringBySourceURLOpt(bookSourceUrl, userNameSpace);
            }
        }
        String str3 = bookSourceString;
        if (str3 == null || str3.length() == 0) {
            String str4 = sourceUrl;
            if (!(str4 == null || str4.length() == 0)) {
                bookSourceString = getBookSourceStringBySourceURLOpt(sourceUrl, userNameSpace);
            }
        }
        return bookSourceString;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v22, types: [T, java.lang.String] */
    @Nullable
    public final String getBookSourceStringBySourceURLOpt(@NotNull String sourceUrl, @NotNull String userNameSpace) throws Exception {
        Intrinsics.checkNotNullParameter(sourceUrl, "sourceUrl");
        Intrinsics.checkNotNullParameter(userNameSpace, "userNameSpace");
        if (StringsKt.isBlank(sourceUrl)) {
            return null;
        }
        File file = ExtKt.getStorageFile$default(new String[]{"data", userNameSpace, "bookSource"}, null, 2, null);
        if (!file.exists()) {
            file = ExtKt.getStorageFile$default(new String[]{"data", "default", "bookSource"}, null, 2, null);
            if (!file.exists()) {
                return null;
            }
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonFactory factory = objectMapper.getFactory();
            final Ref.ObjectRef bookSourceString = new Ref.ObjectRef();
            JsonParser jsonParserCreateParser = factory.createParser(file);
            Throwable th = (Throwable) null;
            try {
                try {
                    JsonParser parser = jsonParserCreateParser;
                    if (parser.nextToken() == JsonToken.START_ARRAY) {
                        while (true) {
                            if (parser.nextToken() == JsonToken.END_ARRAY) {
                                break;
                            }
                            if (parser.currentToken() == JsonToken.START_OBJECT) {
                                TreeNode valueAsTree = parser.readValueAsTree();
                                Intrinsics.checkNotNullExpressionValue(valueAsTree, "parser.readValueAsTree()");
                                JsonNode jsonNode = (JsonNode) valueAsTree;
                                if (sourceUrl.equals(jsonNode.get("bookSourceUrl").asText())) {
                                    bookSourceString.element = jsonNode.toString();
                                    break;
                                }
                            }
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(jsonParserCreateParser, th);
                    BookControllerKt.logger.info(new Function0<Object>() { // from class: com.htmake.reader.api.controller.BookController.getBookSourceStringBySourceURLOpt.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        @Nullable
                        public final Object invoke() {
                            return bookSourceString.element;
                        }
                    });
                    return (String) bookSourceString.element;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(jsonParserCreateParser, th);
                    throw th2;
                }
            } finally {
            }
        } catch (Exception e) {
            BookControllerKt.logger.error("解析文件内容出错: {}  文件: \n{}", e, file);
            throw e;
        }
    }

    @Nullable
    public final Book getShelfBookByURL(@NotNull String url, @NotNull String userNameSpace) {
        JsonArray bookshelf;
        String json;
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(userNameSpace, "userNameSpace");
        if ((url.length() == 0) || (bookshelf = ExtKt.asJsonArray(getUserStorage(userNameSpace, "bookshelf"))) == null) {
            return null;
        }
        int i = 0;
        int size = bookshelf.size();
        if (0 < size) {
            do {
                int i2 = i;
                i++;
                Object map = bookshelf.getJsonObject(i2).getMap();
                Intrinsics.checkNotNullExpressionValue(map, "bookshelf.getJsonObject(i).map");
                if (!(map instanceof String)) {
                    json = ExtKt.getGson().toJson(map);
                } else {
                    json = (String) map;
                }
                String json$iv$iv = json;
                Book _book = (Book) ExtKt.getGson().fromJson(json$iv$iv, new TypeToken<Book>() { // from class: com.htmake.reader.api.controller.BookController$getShelfBookByURL$$inlined$toDataClass$1
                }.getType());
                if (_book.getBookUrl().equals(url)) {
                    _book.setRootDir(ExtKt.getWorkDir$default(null, 1, null));
                    _book.setUserNameSpace(userNameSpace);
                    _book.setInShelf(true);
                    return _book;
                }
            } while (i < size);
            return null;
        }
        return null;
    }

    @Nullable
    public final Object saveShelfBookProgress(@NotNull Book book, @NotNull final BookChapter bookChapter, @NotNull String userNameSpace, @NotNull Continuation<? super Unit> $completion) throws Throwable {
        Object objEditShelfBook = editShelfBook(book, userNameSpace, new Function1<Book, Book>() { // from class: com.htmake.reader.api.controller.BookController.saveShelfBookProgress.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final Book invoke(@NotNull Book existBook) {
                Intrinsics.checkNotNullParameter(existBook, "existBook");
                existBook.setDurChapterIndex(bookChapter.getIndex());
                existBook.setDurChapterTitle(bookChapter.getTitle());
                existBook.setDurChapterTime(System.currentTimeMillis());
                return existBook;
            }
        }, $completion);
        return objEditShelfBook == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEditShelfBook : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object saveShelfBookLatestChapter(@org.jetbrains.annotations.NotNull io.legado.app.data.entities.Book r9, @org.jetbrains.annotations.NotNull java.util.List<io.legado.app.data.entities.BookChapter> r10, @org.jetbrains.annotations.NotNull java.lang.String r11, @org.jetbrains.annotations.Nullable kotlinx.coroutines.sync.Mutex r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 373
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.saveShelfBookLatestChapter(io.legado.app.data.entities.Book, java.util.List, java.lang.String, kotlinx.coroutines.sync.Mutex, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object saveShelfBookLatestChapter$default(BookController bookController, Book book, List list, String str, Mutex mutex, Continuation continuation, int i, Object obj) {
        if ((i & 8) != 0) {
            mutex = null;
        }
        return bookController.saveShelfBookLatestChapter(book, list, str, mutex, continuation);
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0176 A[Catch: all -> 0x02bd, TryCatch #0 {all -> 0x02bd, blocks: (B:16:0x00cb, B:23:0x014c, B:25:0x0176, B:26:0x017f, B:28:0x0193, B:33:0x01ca, B:36:0x01e0, B:41:0x01fe, B:43:0x020d, B:48:0x022b, B:55:0x024d, B:22:0x0144), top: B:67:0x0046 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0193 A[Catch: all -> 0x02bd, LOOP:0: B:28:0x0193->B:72:?, LOOP_START, PHI: r16
  0x0193: PHI (r16v2 int) = (r16v0 int), (r16v3 int) binds: [B:27:0x0190, B:72:?] A[DONT_GENERATE, DONT_INLINE], TryCatch #0 {all -> 0x02bd, blocks: (B:16:0x00cb, B:23:0x014c, B:25:0x0176, B:26:0x017f, B:28:0x0193, B:33:0x01ca, B:36:0x01e0, B:41:0x01fe, B:43:0x020d, B:48:0x022b, B:55:0x024d, B:22:0x0144), top: B:67:0x0046 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x024d A[Catch: all -> 0x02bd, TRY_LEAVE, TryCatch #0 {all -> 0x02bd, blocks: (B:16:0x00cb, B:23:0x014c, B:25:0x0176, B:26:0x017f, B:28:0x0193, B:33:0x01ca, B:36:0x01e0, B:41:0x01fe, B:43:0x020d, B:48:0x022b, B:55:0x024d, B:22:0x0144), top: B:67:0x0046 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x02b2  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object editShelfBook(@org.jetbrains.annotations.NotNull io.legado.app.data.entities.Book r9, @org.jetbrains.annotations.NotNull java.lang.String r10, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super io.legado.app.data.entities.Book, io.legado.app.data.entities.Book> r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super io.legado.app.data.entities.Book> r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 727
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.editShelfBook(io.legado.app.data.entities.Book, java.lang.String, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ void saveBookSources$default(BookController bookController, Book book, List list, String str, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = false;
        }
        bookController.saveBookSources(book, list, str, z);
    }

    public final void saveBookSources(@NotNull Book book, @NotNull List<SearchBook> sourceList, @NotNull String userNameSpace, boolean replace) {
        JsonArray localBookSourceList;
        Intrinsics.checkNotNullParameter(book, "book");
        Intrinsics.checkNotNullParameter(sourceList, "sourceList");
        Intrinsics.checkNotNullParameter(userNameSpace, "userNameSpace");
        if (book.getName().length() == 0) {
            return;
        }
        JsonArray bookSourceList = new JsonArray();
        if (!replace && (localBookSourceList = ExtKt.asJsonArray(getUserStorage(userNameSpace, book.getName() + '_' + book.getAuthor(), "bookSource"))) != null) {
            bookSourceList = localBookSourceList;
        }
        Map urlMap = new LinkedHashMap();
        int i = 0;
        int size = bookSourceList.size();
        if (0 < size) {
            do {
                int i2 = i;
                i++;
                String bookUrl = bookSourceList.getJsonObject(i2).getString("bookUrl");
                Intrinsics.checkNotNullExpressionValue(bookUrl, "bookUrl");
                urlMap.put(bookUrl, Integer.valueOf(i2));
            } while (i < size);
        }
        int i3 = 0;
        int size2 = sourceList.size();
        if (0 < size2) {
            do {
                int k = i3;
                i3++;
                SearchBook searchBook = sourceList.get(k);
                int existIndex = ((Number) urlMap.getOrDefault(searchBook.getBookUrl(), -1)).intValue();
                if (existIndex >= 0) {
                    bookSourceList.set(existIndex, JsonObject.mapFrom(searchBook));
                } else {
                    bookSourceList.add(JsonObject.mapFrom(searchBook));
                    urlMap.put(searchBook.getBookUrl(), Integer.valueOf(bookSourceList.size() - 1));
                }
            } while (i3 < size2);
        }
        saveUserStorage(userNameSpace, ExtKt.getRelativePath(book.getName() + '_' + book.getAuthor(), "bookSource"), bookSourceList);
    }

    public static /* synthetic */ boolean extractEpub$default(BookController bookController, Book book, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return bookController.extractEpub(book, z);
    }

    public final boolean extractEpub(@NotNull Book book, boolean force) {
        Intrinsics.checkNotNullParameter(book, "book");
        File epubExtractDir = new File(ExtKt.getWorkDir(book.getBookUrl() + ((Object) File.separator) + "index"));
        if (force || !epubExtractDir.exists()) {
            ExtKt.deleteRecursively(epubExtractDir);
            File localEpubFile = new File(ExtKt.getWorkDir(book.getOriginName() + ((Object) File.separator) + "index.epub"));
            if (StringsKt.indexOf$default((CharSequence) book.getOriginName(), "localStore", 0, false, 6, (Object) null) > 0) {
                localEpubFile = new File(ExtKt.getWorkDir(book.getOriginName()));
            }
            if (StringsKt.indexOf$default((CharSequence) book.getOriginName(), "webdav", 0, false, 6, (Object) null) > 0) {
                localEpubFile = new File(ExtKt.getWorkDir(book.getOriginName()));
            }
            BookControllerKt.logger.info("extractEpub from {} to {}", localEpubFile, epubExtractDir);
            String string = epubExtractDir.toString();
            Intrinsics.checkNotNullExpressionValue(string, "epubExtractDir.toString()");
            if (!ExtKt.unzip(localEpubFile, string)) {
                return false;
            }
            return true;
        }
        return true;
    }

    public static /* synthetic */ boolean extractCbz$default(BookController bookController, Book book, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return bookController.extractCbz(book, z);
    }

    public final boolean extractCbz(@NotNull Book book, boolean force) {
        Intrinsics.checkNotNullParameter(book, "book");
        File extractDir = new File(ExtKt.getWorkDir(book.getBookUrl() + ((Object) File.separator) + "index"));
        if (force || !extractDir.exists()) {
            ExtKt.deleteRecursively(extractDir);
            File localFile = new File(ExtKt.getWorkDir(book.getOriginName() + ((Object) File.separator) + "index.cbz"));
            if (StringsKt.indexOf$default((CharSequence) book.getOriginName(), "localStore", 0, false, 6, (Object) null) > 0) {
                localFile = new File(ExtKt.getWorkDir(book.getOriginName()));
            }
            if (StringsKt.indexOf$default((CharSequence) book.getOriginName(), "webdav", 0, false, 6, (Object) null) > 0) {
                localFile = new File(ExtKt.getWorkDir(book.getOriginName()));
            }
            String string = extractDir.toString();
            Intrinsics.checkNotNullExpressionValue(string, "extractDir.toString()");
            if (!ExtKt.unzip(localFile, string)) {
                return false;
            }
            return true;
        }
        return true;
    }

    public static /* synthetic */ boolean convertPdfToImage$default(BookController bookController, Book book, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return bookController.convertPdfToImage(book, z);
    }

    public final boolean convertPdfToImage(@NotNull Book book, boolean force) {
        Intrinsics.checkNotNullParameter(book, "book");
        return true;
    }

    public static /* synthetic */ void convertPdfPageToImage$default(BookController bookController, Book book, int i, boolean z, int i2, Object obj) throws IOException {
        if ((i2 & 4) != 0) {
            z = false;
        }
        bookController.convertPdfPageToImage(book, i, z);
    }

    public final void convertPdfPageToImage(@NotNull Book book, int index, boolean force) throws IOException {
        Intrinsics.checkNotNullParameter(book, "book");
        File extractDir = new File(ExtKt.getWorkDir(book.getBookUrl() + ((Object) File.separator) + "index"));
        if (!extractDir.exists()) {
            extractDir.mkdirs();
        }
        File output = new File(extractDir.toString() + ((Object) File.separator) + "output-" + index + '.' + ImgUtil.IMAGE_TYPE_PNG);
        if (force || !output.exists()) {
            ExtKt.deleteRecursively(output);
            File localFile = new File(ExtKt.getWorkDir(book.getOriginName() + ((Object) File.separator) + "index.pdf"));
            if (StringsKt.indexOf$default((CharSequence) book.getOriginName(), "localStore", 0, false, 6, (Object) null) > 0) {
                localFile = new File(ExtKt.getWorkDir(book.getOriginName()));
            }
            if (StringsKt.indexOf$default((CharSequence) book.getOriginName(), "webdav", 0, false, 6, (Object) null) > 0) {
                localFile = new File(ExtKt.getWorkDir(book.getOriginName()));
            }
            PDDocument document = PDDocument.load(localFile);
            PDFRenderer renderer = new PDFRenderer(document);
            float targetWidth = book.getPdfImageWidth();
            Intrinsics.checkNotNullExpressionValue(document, "document");
            savePdfPageToImage(document, renderer, index, targetWidth, ImgUtil.IMAGE_TYPE_PNG, output);
        }
    }

    public final void savePdfPageToImage(@NotNull PDDocument document, @NotNull PDFRenderer renderer, int index, float targetWidth, @NotNull String imageFormat, @NotNull File output) throws IOException {
        Intrinsics.checkNotNullParameter(document, "document");
        Intrinsics.checkNotNullParameter(renderer, "renderer");
        Intrinsics.checkNotNullParameter(imageFormat, "imageFormat");
        Intrinsics.checkNotNullParameter(output, "output");
        PDPage page = document.getPage(index);
        PDRectangle pageSize = page.getCropBox();
        float scaleFactor = targetWidth / pageSize.getWidth();
        float scaledHeight = pageSize.getHeight() * scaleFactor;
        int targetHeightDimension = (0.0f > 0.0f ? 1 : (0.0f == 0.0f ? 0 : -1)) == 0 ? (int) scaledHeight : (int) 0.0f;
        Dimension targetDimension = new Dimension((int) targetWidth, targetHeightDimension);
        BufferedImage image = renderer.renderImageWithDPI(index, 300.0f, ImageType.RGB);
        Image scaledImage = image.getScaledInstance(targetDimension.width, targetDimension.height, 4);
        RenderedImage bufferedImage = new BufferedImage(targetDimension.width, targetDimension.height, 1);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, (ImageObserver) null);
        graphics.dispose();
        ImageIO.write(bufferedImage, imageFormat, output);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public final Object syncBookProgressFromWebdav(@NotNull Object obj, @NotNull String str, @NotNull Continuation<? super Unit> continuation) throws Throwable {
        File file = null;
        if (obj instanceof File) {
            file = (File) obj;
        } else if (obj instanceof String) {
            file = new File((String) obj);
        }
        if (file == null) {
            return Unit.INSTANCE;
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        JsonObject jsonObjectAsJsonObject = ExtKt.asJsonObject(FilesKt.readText$default(file, null, 1, null));
        objectRef.element = jsonObjectAsJsonObject == null ? 0 : (Book) jsonObjectAsJsonObject.mapTo(Book.class);
        if (objectRef.element == 0) {
            return Unit.INSTANCE;
        }
        Object objEditShelfBook = editShelfBook((Book) objectRef.element, str, new Function1<Book, Book>() { // from class: com.htmake.reader.api.controller.BookController.syncBookProgressFromWebdav.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final Book invoke(@NotNull Book existBook) {
                Intrinsics.checkNotNullParameter(existBook, "existBook");
                existBook.setDurChapterIndex(objectRef.element.getDurChapterIndex());
                existBook.setDurChapterPos(objectRef.element.getDurChapterPos());
                existBook.setDurChapterTime(objectRef.element.getDurChapterTime());
                existBook.setDurChapterTitle(objectRef.element.getDurChapterTitle());
                BookControllerKt.logger.info("syncShelfBookProgress: {}", existBook);
                return existBook;
            }
        }, continuation);
        return objEditShelfBook == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objEditShelfBook : Unit.INSTANCE;
    }

    @Nullable
    public final Object saveBookProgressToWebdav(@NotNull Book book, @NotNull BookChapter bookChapter, @NotNull String userNameSpace, @NotNull Continuation<? super Unit> $completion) {
        String userHome = getUserWebdavHome(userNameSpace);
        File bookProgressDir = new File(userHome + ((Object) File.separator) + "bookProgress");
        if (!bookProgressDir.exists()) {
            bookProgressDir = new File(userHome + ((Object) File.separator) + "legado" + ((Object) File.separator) + "bookProgress");
            if (!bookProgressDir.exists()) {
                return Unit.INSTANCE;
            }
        }
        File progressFile = new File(bookProgressDir.toString() + ((Object) File.separator) + book.getName() + '_' + book.getAuthor() + ".json");
        FilesKt.writeText$default(progressFile, ExtKt.jsonEncode(MapsKt.mapOf(TuplesKt.to("name", book.getName()), TuplesKt.to("author", book.getAuthor()), TuplesKt.to("durChapterIndex", Boxing.boxInt(bookChapter.getIndex())), TuplesKt.to("durChapterPos", Boxing.boxInt(0)), TuplesKt.to("durChapterTime", Boxing.boxLong(System.currentTimeMillis())), TuplesKt.to("durChapterTitle", bookChapter.getTitle())), true), null, 2, null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Failed to calculate best type for var: r12v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r12v1 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Multi-variable type inference failed. Error: java.lang.NullPointerException
     */
    /* JADX WARN: Not initialized variable reg: 12, insn: 0x033c: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r12 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) A[TRY_LEAVE] (LINE:2519), block:B:49:0x0337 */
    /* JADX WARN: Not initialized variable reg: 12, insn: 0x0346: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r12 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) A[TRY_LEAVE] (LINE:2519), block:B:52:0x0346 */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0284 A[Catch: Exception -> 0x0335, all -> 0x0344, TRY_LEAVE, TryCatch #1 {Exception -> 0x0335, blocks: (B:11:0x0091, B:13:0x00ab, B:16:0x00b9, B:17:0x00ef, B:19:0x00f9, B:21:0x0137, B:23:0x0179, B:25:0x01a6, B:26:0x01ec, B:28:0x0218, B:29:0x0248, B:31:0x0250, B:33:0x0258, B:36:0x0284, B:43:0x031f, B:45:0x0327, B:42:0x0317), top: B:59:0x0043, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object syncFromWebdav(@org.jetbrains.annotations.NotNull java.lang.String r8, @org.jetbrains.annotations.NotNull java.lang.String r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 862
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.syncFromWebdav(java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00db A[ADDED_TO_REGION, REMOVE] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object saveToWebdav(@org.jetbrains.annotations.NotNull java.lang.String r9, @org.jetbrains.annotations.Nullable java.lang.String r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r11) {
        /*
            Method dump skipped, instructions count: 379
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.saveToWebdav(java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object saveToWebdav$default(BookController bookController, String str, String str2, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        return bookController.saveToWebdav(str, str2, continuation);
    }

    public static /* synthetic */ Object createUserBackup$default(BookController bookController, String str, String str2, String str3, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = null;
        }
        return bookController.createUserBackup(str, str2, str3, continuation);
    }

    @Nullable
    public final Object createUserBackup(@NotNull String userNameSpace, @NotNull String backupDir, @Nullable String latestZipFilePath, @NotNull Continuation<? super File> $completion) {
        String today = new SimpleDateFormat("yyyy-MM-dd").format(Boxing.boxLong(System.currentTimeMillis()));
        String workDir = ExtKt.getWorkDir("storage", "data", userNameSpace, Intrinsics.stringPlus("backup", today));
        File descDirFile = new File(workDir);
        ExtKt.deleteRecursively(descDirFile);
        try {
            if (latestZipFilePath != null) {
                try {
                    if (!ExtKt.unzip(new File(latestZipFilePath), workDir)) {
                        ExtKt.deleteRecursively(descDirFile);
                        return null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ExtKt.deleteRecursively(descDirFile);
                    return null;
                }
            }
            String[] backupFileNames = getBackupFileNames();
            Iterable syncDataFileList = CollectionsKt.arrayListOf(Arrays.copyOf(backupFileNames, backupFileNames.length));
            Iterable $this$forEach$iv = syncDataFileList;
            for (Object element$iv : $this$forEach$iv) {
                String it = (String) element$iv;
                File userDataFile = new File(ExtKt.getWorkDir("storage", "data", userNameSpace, it));
                if (userDataFile.exists()) {
                    File backupFile = new File(workDir + ((Object) File.separator) + it);
                    ExtKt.deleteRecursively(backupFile);
                    FilesKt.copyRecursively$default(userDataFile, backupFile, false, null, 6, null);
                }
            }
            File webdavBooksDir = new File(ExtKt.getWorkDir("storage", "data", userNameSpace, "webdav", "books"));
            if (webdavBooksDir.exists()) {
                File backupBooksDir = new File(workDir + ((Object) File.separator) + "books");
                ExtKt.deleteRecursively(backupBooksDir);
                FilesKt.copyRecursively$default(webdavBooksDir, backupBooksDir, false, null, 6, null);
            }
            File backupFile2 = FileUtils.INSTANCE.createFileWithReplace(backupDir + ((Object) File.separator) + "backup" + ((Object) today) + ".zip");
            ZipUtils zipUtils = ZipUtils.INSTANCE;
            File[] fileArrListFiles = descDirFile.listFiles();
            Intrinsics.checkNotNullExpressionValue(fileArrListFiles, "descDirFile.listFiles()");
            File file = zipUtils.zipFiles(CollectionsKt.arrayListOf(Arrays.copyOf(fileArrListFiles, fileArrListFiles.length)), backupFile2, (String) null) ? backupFile2 : (File) null;
            ExtKt.deleteRecursively(descDirFile);
            return file;
        } catch (Throwable th) {
            ExtKt.deleteRecursively(descDirFile);
            throw th;
        }
    }

    @Nullable
    public final Object getLastBackFileFromWebdav(@NotNull String userNameSpace, @NotNull Continuation<? super String> $completion) {
        String userHome = getUserWebdavHome(userNameSpace);
        File legadoHome = new File(userHome + ((Object) File.separator) + "legado");
        if (!legadoHome.exists()) {
            legadoHome = new File(userHome);
        }
        if (!legadoHome.exists()) {
            return null;
        }
        Object latestZipFile = null;
        Regex zipFileReg = new Regex("^backup[0-9-]+.zip$", RegexOption.IGNORE_CASE);
        Object[] it = legadoHome.listFiles();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        if (it.length > 1) {
            ArraysKt.sortWith(it, new Comparator<T>() { // from class: com.htmake.reader.api.controller.BookController$getLastBackFileFromWebdav$lambda-16$$inlined$sortByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    File it2 = (File) t2;
                    File it3 = (File) t;
                    return ComparisonsKt.compareValues(Long.valueOf(it2.lastModified()), Long.valueOf(it3.lastModified()));
                }
            });
        }
        Intrinsics.checkNotNullExpressionValue(it, "legadoHome.listFiles().also{\n            it.sortByDescending {\n                it.lastModified()\n            }\n        }");
        Object[] $this$forEach$iv = it;
        for (Object element$iv : $this$forEach$iv) {
            File it2 = (File) element$iv;
            String name = it2.getName();
            Intrinsics.checkNotNullExpressionValue(name, "it.name");
            if (zipFileReg.matches(name)) {
                latestZipFile = it2.toString();
            }
        }
        return latestZipFile;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object bookSourceDebugSSE(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r11) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 904
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.bookSourceDebugSSE(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* renamed from: bookSourceDebugSSE$lambda-18, reason: not valid java name */
    private static final void m835bookSourceDebugSSE$lambda18(BookController this$0, Void it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BookControllerKt.logger.info("客户端已断开链接，停止 bookSourceDebugSSE");
        JobKt__JobKt.cancel$default(this$0.getCoroutineContext(), (CancellationException) null, 1, (Object) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0486  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x048e  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x04c8  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x05d2  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x062e  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0633  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x06d4  */
    /* JADX WARN: Type inference failed for: r1v12, types: [T, java.util.Set] */
    /* JADX WARN: Type inference failed for: r1v24, types: [T, java.util.Set] */
    /* JADX WARN: Type inference failed for: r1v45, types: [T, java.lang.String] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object cacheBookSSE(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1934
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.cacheBookSSE(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* renamed from: cacheBookSSE$lambda-19, reason: not valid java name */
    private static final void m836cacheBookSSE$lambda19(Ref.BooleanRef isEnd, BookController this$0, Void it) {
        Intrinsics.checkNotNullParameter(isEnd, "$isEnd");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BookControllerKt.logger.info("客户端已断开链接，停止 cacheBookSSE");
        isEnd.element = true;
        JobKt__JobKt.cancel$default(this$0.getCoroutineContext(), (CancellationException) null, 1, (Object) null);
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\u0010\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", ""})
    @DebugMetadata(f = "BookController.kt", l = {2741, 2745}, i = {0, 0, 0, 0}, s = {"L$0", "L$1", "I$0", "I$1"}, n = {"$this$limitConcurrent", "chapterInfo", "it", "chapterIndex"}, m = "invokeSuspend", c = "com.htmake.reader.api.controller.BookController$cacheBookSSE$3")
    /* renamed from: com.htmake.reader.api.controller.BookController$cacheBookSSE$3, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$cacheBookSSE$3.class */
    static final class C00673 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Object>, Object> {
        int I$1;
        Object L$1;
        int label;
        private /* synthetic */ Object L$0;
        /* synthetic */ int I$0;
        final /* synthetic */ Ref.ObjectRef<Set<Integer>> $cachedChapterContentSet;
        final /* synthetic */ Ref.ObjectRef<List<BookChapter>> $chapterList;
        final /* synthetic */ Ref.ObjectRef<String> $bookSource;
        final /* synthetic */ BookController this$0;
        final /* synthetic */ Ref.ObjectRef<String> $userNameSpace;
        final /* synthetic */ Book $bookInfo;
        final /* synthetic */ File $localCacheDir;
        final /* synthetic */ Ref.IntRef $successCount;
        final /* synthetic */ Ref.BooleanRef $isEnd;
        final /* synthetic */ Ref.IntRef $failedCount;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00673(Ref.ObjectRef<Set<Integer>> $cachedChapterContentSet, Ref.ObjectRef<List<BookChapter>> $chapterList, Ref.ObjectRef<String> $bookSource, BookController this$0, Ref.ObjectRef<String> $userNameSpace, Book $bookInfo, File $localCacheDir, Ref.IntRef $successCount, Ref.BooleanRef $isEnd, Ref.IntRef $failedCount, Continuation<? super C00673> $completion) {
            super(3, $completion);
            this.$cachedChapterContentSet = $cachedChapterContentSet;
            this.$chapterList = $chapterList;
            this.$bookSource = $bookSource;
            this.this$0 = this$0;
            this.$userNameSpace = $userNameSpace;
            this.$bookInfo = $bookInfo;
            this.$localCacheDir = $localCacheDir;
            this.$successCount = $successCount;
            this.$isEnd = $isEnd;
            this.$failedCount = $failedCount;
        }

        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, int p2, @Nullable Continuation<Object> p3) {
            C00673 c00673 = new C00673(this.$cachedChapterContentSet, this.$chapterList, this.$bookSource, this.this$0, this.$userNameSpace, this.$bookInfo, this.$localCacheDir, this.$successCount, this.$isEnd, this.$failedCount, p3);
            c00673.L$0 = p1;
            c00673.I$0 = p2;
            return c00673.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Object> continuation) {
            return invoke(coroutineScope, num.intValue(), continuation);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0176  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x017a  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0186 A[Catch: Exception -> 0x021f, TryCatch #0 {Exception -> 0x021f, blocks: (B:7:0x0063, B:9:0x007d, B:10:0x009c, B:17:0x0119, B:21:0x017c, B:23:0x0186, B:25:0x01af, B:32:0x01f2, B:16:0x0113, B:31:0x01ec), top: B:39:0x0009 }] */
        /* JADX WARN: Removed duplicated region for block: B:24:0x01ad  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x01de  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r36) throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 588
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.C00673.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object cacheBookOnServer(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 356
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.cacheBookOnServer(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "BookController.kt", l = {2800}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.controller.BookController$cacheBookOnServer$2")
    /* renamed from: com.htmake.reader.api.controller.BookController$cacheBookOnServer$2, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$cacheBookOnServer$2.class */
    static final class C00652 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ JsonArray $bookUrlList;
        final /* synthetic */ String $userNameSpace;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00652(JsonArray $bookUrlList, String $userNameSpace, Continuation<? super C00652> $completion) {
            super(2, $completion);
            this.$bookUrlList = $bookUrlList;
            this.$userNameSpace = $userNameSpace;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            return BookController.this.new C00652(this.$bookUrlList, this.$userNameSpace, $completion);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
            return ((C00652) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    if (BookController.this.cacheBookOnServer(this.$bookUrlList, this.$userNameSpace, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0072, code lost:
    
        if (0 < r40) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0075, code lost:
    
        r0 = r39;
        r39 = r39 + 1;
        r0 = r36.getString(r0);
        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, "bookUrl");
        r43 = r35.getShelfBookByURL(r0, r37);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0097, code lost:
    
        if (r43 != null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x009a, code lost:
    
        com.htmake.reader.api.controller.BookControllerKt.logger.info("未找到书籍信息: {}", r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x00af, code lost:
    
        if (r43.isLocalBook() == false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x00b2, code lost:
    
        com.htmake.reader.api.controller.BookControllerKt.logger.info("本地书籍跳过缓存: {}", r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x00c2, code lost:
    
        com.htmake.reader.api.controller.BookControllerKt.logger.info("开始缓存书籍: {}", r43);
        r44 = r35.getBookSourceStringBySourceURLOpt(r43.getOrigin(), r37);
        r0 = r44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x00ea, code lost:
    
        if (r0 == null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00f4, code lost:
    
        if (r0.length() != 0) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00f7, code lost:
    
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00fb, code lost:
    
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00fc, code lost:
    
        if (r0 == false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00ff, code lost:
    
        com.htmake.reader.api.controller.BookControllerKt.logger.info("未找到书源信息: {}", r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x010f, code lost:
    
        r60.L$0 = r35;
        r60.L$1 = r36;
        r60.L$2 = r37;
        r60.L$3 = r43;
        r60.L$4 = r44;
        r60.L$5 = null;
        r60.L$6 = null;
        r60.L$7 = null;
        r60.L$8 = null;
        r60.I$0 = r39;
        r60.I$1 = r40;
        r60.label = 1;
        r0 = getLocalChapterList$default(r35, r43, r44, false, r37, false, null, r60, 48, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x016f, code lost:
    
        if (r0 != r0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0174, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x01e2, code lost:
    
        if (0 <= r49) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x01e5, code lost:
    
        r0 = r48;
        r48 = r48 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x01f8, code lost:
    
        if (r46.contains(kotlin.coroutines.jvm.internal.Boxing.boxInt(r0)) != false) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x01fb, code lost:
    
        r51 = r0;
        r52 = (io.legado.app.data.entities.BookChapter) r45.get(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x020d, code lost:
    
        r53 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x021c, code lost:
    
        if ((r51 + 1) >= r45.size()) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x021f, code lost:
    
        r0 = (io.legado.app.data.entities.BookChapter) r45.get(r51 + 1);
        r53 = r0.getUrl();
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0236, code lost:
    
        r60.L$0 = r35;
        r60.L$1 = r36;
        r60.L$2 = r37;
        r60.L$3 = r43;
        r60.L$4 = r44;
        r60.L$5 = r45;
        r60.L$6 = r46;
        r60.L$7 = r47;
        r60.L$8 = r52;
        r60.I$0 = r39;
        r60.I$1 = r40;
        r60.I$2 = r48;
        r60.I$3 = r49;
        r60.I$4 = r51;
        r60.label = 2;
        r0 = new io.legado.app.model.webBook.WebBook(r44, r35.getAppConfig().getDebugLog(), (io.legado.app.model.DebugLog) null, r37, 4, (kotlin.jvm.internal.DefaultConstructorMarker) null).getBookContent(r43, r52, r53, r60);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x02bd, code lost:
    
        if (r0 != r0) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x02c2, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x04ed, code lost:
    
        if (r48 <= r49) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x04f0, code lost:
    
        com.htmake.reader.api.controller.BookControllerKt.logger.info("缓存书籍完成: {}", r43);
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0501, code lost:
    
        if (r39 < r40) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0507, code lost:
    
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:65:0x04ed -> B:33:0x01e5). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:68:0x0501 -> B:12:0x0075). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object cacheBookOnServer(@org.jetbrains.annotations.NotNull io.vertx.core.json.JsonArray r36, @org.jetbrains.annotations.NotNull java.lang.String r37, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r38) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 1299
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.cacheBookOnServer(io.vertx.core.json.JsonArray, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object deleteBookCache(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 441
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.deleteBookCache(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object textToSpeech(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 971
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.textToSpeech(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "BookController.kt", l = {2942, 2943, 2944}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "com.htmake.reader.api.controller.BookController$textToSpeech$2")
    /* renamed from: com.htmake.reader.api.controller.BookController$textToSpeech$2, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$textToSpeech$2.class */
    static final class C01392 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ Ref.ObjectRef<String> $type;
        final /* synthetic */ BookController this$0;
        final /* synthetic */ HttpServerResponse $response;
        final /* synthetic */ Ref.ObjectRef<String> $text;
        final /* synthetic */ Map<String, String> $options;
        final /* synthetic */ RoutingContext $context;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01392(Ref.ObjectRef<String> $type, BookController this$0, HttpServerResponse $response, Ref.ObjectRef<String> $text, Map<String, String> $options, RoutingContext $context, Continuation<? super C01392> $completion) {
            super(2, $completion);
            this.$type = $type;
            this.this$0 = this$0;
            this.$response = $response;
            this.$text = $text;
            this.$options = $options;
            this.$context = $context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            return new C01392(this.$type, this.this$0, this.$response, this.$text, this.$options, this.$context, $completion);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
            return ((C01392) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    String str = this.$type.element;
                    if (Intrinsics.areEqual(str, "edge")) {
                        BookController bookController = this.this$0;
                        HttpServerResponse response = this.$response;
                        Intrinsics.checkNotNullExpressionValue(response, "response");
                        this.label = 1;
                        if (bookController.ttsByEdge(response, this.$text.element, this.$options, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (Intrinsics.areEqual(str, "textToSpeechCn")) {
                        BookController bookController2 = this.this$0;
                        HttpServerResponse response2 = this.$response;
                        Intrinsics.checkNotNullExpressionValue(response2, "response");
                        this.label = 2;
                        if (bookController2.ttsByTextToSpeechCn(response2, this.$text.element, this.$options, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        BookController bookController3 = this.this$0;
                        HttpServerResponse response3 = this.$response;
                        Intrinsics.checkNotNullExpressionValue(response3, "response");
                        this.label = 3;
                        if (bookController3.ttsByApi(response3, this.$text.element, this.this$0.getUserNameSpace(this.$context), this.$options, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                case 2:
                    ResultKt.throwOnFailure($result);
                    break;
                case 3:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public static /* synthetic */ Object ttsByEdge$default(BookController bookController, HttpServerResponse httpServerResponse, String str, Map map, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            map = null;
        }
        return bookController.ttsByEdge(httpServerResponse, str, map, continuation);
    }

    @Nullable
    public final Object ttsByEdge(@NotNull HttpServerResponse response, @NotNull String text, @Nullable Map<String, String> options, @NotNull Continuation<? super Unit> $completion) {
        VoiceEnum voice = VoiceEnum.zh_CN_XiaoxiaoNeural;
        String rate = "0";
        String pitch = "0%";
        if (options != null) {
            if (options.containsKey("voice")) {
                VoiceEnum voiceEnumFromSortName = VoiceEnum.fromSortName(options.get("voice"));
                voice = voiceEnumFromSortName == null ? VoiceEnum.zh_CN_XiaoxiaoNeural : voiceEnumFromSortName;
            }
            if (options.containsKey("rate")) {
                String str = options.get("rate");
                rate = str == null ? "0" : str;
            }
            if (options.containsKey("pitch")) {
                pitch = Intrinsics.stringPlus(options.get("pitch"), QuickTargetSourceCreator.PREFIX_THREAD_LOCAL);
            }
        }
        TTSService ts = TTSService.builder().build();
        SSML ssml = SSML.builder().synthesisText(text).voice(voice).rate(rate).pitch(pitch).style(TtsStyleEnum.chat).build();
        byte[] mp3byte = ts.sendText(ssml);
        if (options != null && CustomBooleanEditor.VALUE_1.equals(options.get(HttpHeaders.Values.BASE64))) {
            ReturnData returnData = new ReturnData();
            HttpServerResponse httpServerResponsePutHeader = response.putHeader("content-type", "application/json; charset=utf-8");
            String strEncodeToString = Base64.getEncoder().encodeToString(mp3byte);
            Intrinsics.checkNotNullExpressionValue(strEncodeToString, "getEncoder().encodeToString(mp3byte)");
            httpServerResponsePutHeader.end(ExtKt.jsonEncode$default(ReturnData.setData$default(returnData, strEncodeToString, null, 2, null), false, 2, null));
        } else {
            response.putHeader("Content-Type", "audio/mpeg").end(Buffer.buffer(mp3byte));
        }
        return Unit.INSTANCE;
    }

    @Nullable
    public final HttpTTS getHttpTTSByName(@NotNull String name, @NotNull String userNameSpace) {
        JsonArray list;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(userNameSpace, "userNameSpace");
        if ((name.length() == 0) || (list = ExtKt.asJsonArray(getUserStorage(userNameSpace, "httpTTS"))) == null) {
            return null;
        }
        int i = 0;
        int size = list.size();
        if (0 < size) {
            do {
                int i2 = i;
                i++;
                HttpTTS.Companion companion = HttpTTS.Companion;
                String string = list.getJsonObject(i2).toString();
                Intrinsics.checkNotNullExpressionValue(string, "list.getJsonObject(i).toString()");
                Object objM1097fromJsonIoAF18A = companion.m1097fromJsonIoAF18A(string);
                HttpTTS httpTTS = (HttpTTS) (Result.m2101isFailureimpl(objM1097fromJsonIoAF18A) ? null : objM1097fromJsonIoAF18A);
                if (httpTTS != null && httpTTS.getName().equals(name)) {
                    return httpTTS;
                }
            } while (i < size);
            return null;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object ttsByApi(@org.jetbrains.annotations.NotNull io.vertx.core.http.HttpServerResponse r9, @org.jetbrains.annotations.NotNull java.lang.String r10, @org.jetbrains.annotations.NotNull java.lang.String r11, @org.jetbrains.annotations.Nullable java.util.Map<java.lang.String, java.lang.String> r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r13) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 553
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.ttsByApi(io.vertx.core.http.HttpServerResponse, java.lang.String, java.lang.String, java.util.Map, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object ttsByApi$default(BookController bookController, HttpServerResponse httpServerResponse, String str, String str2, Map map, Continuation continuation, int i, Object obj) {
        if ((i & 8) != 0) {
            map = null;
        }
        return bookController.ttsByApi(httpServerResponse, str, str2, map, continuation);
    }

    /* JADX WARN: Failed to apply debug info
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r0v115 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r0v138 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r0v151 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r21v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r21v1 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r21v5 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 21, insn: 0x0303: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = 
  (r21 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('downloadErrorNo' kotlin.jvm.internal.Ref$IntRef)])
 (LINE:3078), block:B:79:0x0303 */
    /* JADX WARN: Not initialized variable reg: 21, insn: 0x033b: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = 
  (r21 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('downloadErrorNo' kotlin.jvm.internal.Ref$IntRef)])
 (LINE:3085), block:B:83:0x033b */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0164 A[Catch: Exception -> 0x02af, TryCatch #0 {Exception -> 0x02af, blocks: (B:12:0x006b, B:19:0x0141, B:31:0x0187, B:33:0x019c, B:34:0x01a6, B:35:0x01a7, B:36:0x01af, B:60:0x027e, B:39:0x01ca, B:41:0x01ec, B:42:0x0205, B:55:0x0236, B:57:0x025c, B:58:0x027b, B:46:0x0213, B:22:0x0164, B:18:0x0139), top: B:92:0x0046 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0187 A[Catch: Exception -> 0x02af, TryCatch #0 {Exception -> 0x02af, blocks: (B:12:0x006b, B:19:0x0141, B:31:0x0187, B:33:0x019c, B:34:0x01a6, B:35:0x01a7, B:36:0x01af, B:60:0x027e, B:39:0x01ca, B:41:0x01ec, B:42:0x0205, B:55:0x0236, B:57:0x025c, B:58:0x027b, B:46:0x0213, B:22:0x0164, B:18:0x0139), top: B:92:0x0046 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x01ca A[Catch: Exception -> 0x02af, TryCatch #0 {Exception -> 0x02af, blocks: (B:12:0x006b, B:19:0x0141, B:31:0x0187, B:33:0x019c, B:34:0x01a6, B:35:0x01a7, B:36:0x01af, B:60:0x027e, B:39:0x01ca, B:41:0x01ec, B:42:0x0205, B:55:0x0236, B:57:0x025c, B:58:0x027b, B:46:0x0213, B:22:0x0164, B:18:0x0139), top: B:92:0x0046 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /* JADX WARN: Type inference failed for: r0v115, types: [kotlin.jvm.internal.Ref$IntRef] */
    /* JADX WARN: Type inference failed for: r0v138, types: [kotlin.jvm.internal.Ref$IntRef] */
    /* JADX WARN: Type inference failed for: r0v151, types: [kotlin.jvm.internal.Ref$IntRef] */
    /* JADX WARN: Type inference failed for: r1v32, types: [T, okhttp3.Response] */
    /* JADX WARN: Type inference failed for: r21v0, names: [downloadErrorNo], types: [kotlin.jvm.internal.Ref$IntRef] */
    /* JADX WARN: Type inference failed for: r21v1, names: [downloadErrorNo], types: [kotlin.jvm.internal.Ref$IntRef] */
    /* JADX WARN: Type inference failed for: r36v0, types: [T] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getSpeakStream(@org.jetbrains.annotations.NotNull io.legado.app.data.entities.HttpTTS r17, @org.jetbrains.annotations.NotNull java.lang.String r18, int r19, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.io.InputStream> r20) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 930
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.getSpeakStream(io.legado.app.data.entities.HttpTTS, java.lang.String, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object ttsByTextToSpeechCn(@org.jetbrains.annotations.NotNull io.vertx.core.http.HttpServerResponse r7, @org.jetbrains.annotations.NotNull java.lang.String r8, @org.jetbrains.annotations.Nullable java.util.Map<java.lang.String, java.lang.String> r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 527
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.ttsByTextToSpeechCn(io.vertx.core.http.HttpServerResponse, java.lang.String, java.util.Map, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object ttsByTextToSpeechCn$default(BookController bookController, HttpServerResponse httpServerResponse, String str, Map map, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            map = null;
        }
        return bookController.ttsByTextToSpeechCn(httpServerResponse, str, map, continuation);
    }

    /* compiled from: BookController.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    /* renamed from: com.htmake.reader.api.controller.BookController$ttsByTextToSpeechCn$2, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/com/htmake/reader/api/controller/BookController$ttsByTextToSpeechCn$2.class */
    /* synthetic */ class C01422 extends AdaptedFunctionReference implements Function2<String, String, Unit> {
        C01422(CaseInsensitiveHeaders receiver) {
            super(2, receiver, CaseInsensitiveHeaders.class, BeanUtil.PREFIX_ADDER, "add(Ljava/lang/String;Ljava/lang/String;)Lio/vertx/core/MultiMap;", 8);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(String p0, String p1) {
            BookController.ttsByTextToSpeechCn$add((CaseInsensitiveHeaders) this.receiver, p0, p1);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(String str, String str2) {
            invoke2(str, str2);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ void ttsByTextToSpeechCn$add(CaseInsensitiveHeaders $this$ttsByTextToSpeechCn_u24add, String p0, String p1) {
        $this$ttsByTextToSpeechCn_u24add.add(p0, p1);
    }

    @NotNull
    public final File getChapterCacheDir(@NotNull Book bookInfo, @NotNull String userNameSpace) {
        Intrinsics.checkNotNullParameter(bookInfo, "bookInfo");
        Intrinsics.checkNotNullParameter(userNameSpace, "userNameSpace");
        String md5Encode = MD5Utils.INSTANCE.md5Encode(bookInfo.getBookUrl()).toString();
        String localCacheDirPath = ExtKt.getWorkDir("storage", "data", userNameSpace, bookInfo.getName() + '_' + bookInfo.getAuthor(), md5Encode);
        File localCacheDir = new File(localCacheDirPath);
        if (!localCacheDir.exists()) {
            localCacheDir.mkdirs();
        }
        return localCacheDir;
    }

    @NotNull
    public final Set<Integer> getCachedChapterContentSet(@NotNull Book bookInfo, @NotNull String userNameSpace) {
        Intrinsics.checkNotNullParameter(bookInfo, "bookInfo");
        Intrinsics.checkNotNullParameter(userNameSpace, "userNameSpace");
        File localCacheDir = getChapterCacheDir(bookInfo, userNameSpace);
        Set cachedChapterContentSet = new LinkedHashSet();
        Object[] objArrListFiles = localCacheDir.listFiles();
        Intrinsics.checkNotNullExpressionValue(objArrListFiles, "localCacheDir.listFiles()");
        Object[] $this$forEach$iv = objArrListFiles;
        for (Object element$iv : $this$forEach$iv) {
            File it = (File) element$iv;
            String name = it.getName();
            Intrinsics.checkNotNullExpressionValue(name, "it.name");
            if (!StringsKt.startsWith$default(name, ".", false, 2, (Object) null)) {
                String name2 = it.getName();
                Intrinsics.checkNotNullExpressionValue(name2, "it.name");
                if (StringsKt.endsWith$default(name2, ".txt", false, 2, (Object) null)) {
                    String name3 = it.getName();
                    Intrinsics.checkNotNullExpressionValue(name3, "it.name");
                    cachedChapterContentSet.add(Integer.valueOf(Integer.parseInt(StringsKt.replace$default(name3, ".txt", "", false, 4, (Object) null))));
                }
            }
        }
        return cachedChapterContentSet;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0147 A[LOOP:0: B:26:0x0147->B:37:?, LOOP_START, PHI: r14
  0x0147: PHI (r14v2 int) = (r14v1 int), (r14v3 int) binds: [B:25:0x0144, B:37:?] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getShelfBookWithCacheInfo(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 448
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.getShelfBookWithCacheInfo(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x033f  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x03a5  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x03f6  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object exportBook(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1166
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.exportBook(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object exportToTxt(@org.jetbrains.annotations.NotNull java.io.File r10, @org.jetbrains.annotations.NotNull io.legado.app.data.entities.Book r11, @org.jetbrains.annotations.NotNull java.lang.String r12, @org.jetbrains.annotations.NotNull java.lang.String r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.io.File> r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 251
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.exportToTxt(java.io.File, io.legado.app.data.entities.Book, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getAllContents(io.legado.app.data.entities.Book r14, java.lang.String r15, java.lang.String r16, kotlin.jvm.functions.Function2<? super java.lang.String, ? super java.util.ArrayList<kotlin.Triple<java.lang.String, java.lang.Integer, java.lang.String>>, kotlin.Unit> r17, kotlin.coroutines.Continuation<? super kotlin.Unit> r18) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 536
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.getAllContents(io.legado.app.data.entities.Book, java.lang.String, java.lang.String, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object exportToEpub(java.io.File r11, io.legado.app.data.entities.Book r12, java.lang.String r13, java.lang.String r14, kotlin.coroutines.Continuation<? super java.io.File> r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 473
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.exportToEpub(java.io.File, io.legado.app.data.entities.Book, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final String setAssets(Book book, EpubBook epubBook) {
        Resources resources = epubBook.getResources();
        URL resource = BookController.class.getResource("/epub/fonts.css");
        Intrinsics.checkNotNullExpressionValue(resource, "BookController::class.java.getResource(\"/epub/fonts.css\")");
        resources.add(new Resource(TextStreamsKt.readBytes(resource), "Styles/fonts.css"));
        Resources resources2 = epubBook.getResources();
        URL resource2 = BookController.class.getResource("/epub/main.css");
        Intrinsics.checkNotNullExpressionValue(resource2, "BookController::class.java.getResource(\"/epub/main.css\")");
        resources2.add(new Resource(TextStreamsKt.readBytes(resource2), "Styles/main.css"));
        Resources resources3 = epubBook.getResources();
        URL resource3 = BookController.class.getResource("/epub/logo.png");
        Intrinsics.checkNotNullExpressionValue(resource3, "BookController::class.java.getResource(\"/epub/logo.png\")");
        resources3.add(new Resource(TextStreamsKt.readBytes(resource3), "Images/logo.png"));
        String name = book.getName();
        String realAuthor = book.getRealAuthor();
        String displayIntro = book.getDisplayIntro();
        String kind = book.getKind();
        String wordCount = book.getWordCount();
        URL resource4 = BookController.class.getResource("/epub/cover.html");
        Intrinsics.checkNotNullExpressionValue(resource4, "BookController::class.java.getResource(\"/epub/cover.html\")");
        epubBook.addSection("封面", ResourceUtil.createPublicResource(name, realAuthor, displayIntro, kind, wordCount, new String(TextStreamsKt.readBytes(resource4), Charsets.UTF_8), "Text/cover.html"));
        String name2 = book.getName();
        String realAuthor2 = book.getRealAuthor();
        String displayIntro2 = book.getDisplayIntro();
        String kind2 = book.getKind();
        String wordCount2 = book.getWordCount();
        URL resource5 = BookController.class.getResource("/epub/intro.html");
        Intrinsics.checkNotNullExpressionValue(resource5, "BookController::class.java.getResource(\"/epub/intro.html\")");
        epubBook.addSection("简介", ResourceUtil.createPublicResource(name2, realAuthor2, displayIntro2, kind2, wordCount2, new String(TextStreamsKt.readBytes(resource5), Charsets.UTF_8), "Text/intro.html"));
        URL resource6 = BookController.class.getResource("/epub/chapter.html");
        Intrinsics.checkNotNullExpressionValue(resource6, "BookController::class.java.getResource(\"/epub/chapter.html\")");
        return new String(TextStreamsKt.readBytes(resource6), Charsets.UTF_8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object setCover(io.legado.app.data.entities.Book r17, me.ag2s.epublib.domain.EpubBook r18, java.lang.String r19, kotlin.coroutines.Continuation<? super kotlin.Unit> r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 540
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.setCover(io.legado.app.data.entities.Book, me.ag2s.epublib.domain.EpubBook, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object setEpubContent(java.lang.String r14, io.legado.app.data.entities.Book r15, me.ag2s.epublib.domain.EpubBook r16, java.lang.String r17, java.lang.String r18, kotlin.coroutines.Continuation<? super kotlin.Unit> r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 586
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.setEpubContent(java.lang.String, io.legado.app.data.entities.Book, me.ag2s.epublib.domain.EpubBook, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final String fixPic(EpubBook epubBook, Book book, String content, BookChapter chapter) {
        StringBuilder data = new StringBuilder("");
        Iterable $this$forEach$iv = StringsKt.split$default((CharSequence) content, new String[]{"\n"}, false, 0, 6, (Object) null);
        for (Object element$iv : $this$forEach$iv) {
            String text = (String) element$iv;
            String strReplace$default = text;
            Matcher matcher = AppPattern.INSTANCE.getImgPattern().matcher(text);
            while (matcher.find()) {
                String it = matcher.group(1);
                if (it != null) {
                    String src = NetworkUtils.INSTANCE.getAbsoluteURL(chapter.getUrl(), it);
                    String originalHref = MD5Utils.INSTANCE.md5Encode16(src) + '.' + BookHelp.INSTANCE.getImageSuffix(src);
                    String href = Intrinsics.stringPlus("Images/", originalHref);
                    File vFile = BookHelp.INSTANCE.getImage(book, src);
                    if (vFile.exists()) {
                        FileResourceProvider fp = new FileResourceProvider(vFile.getParent());
                        LazyResource img = new LazyResource(fp, href, originalHref);
                        epubBook.getResources().add(img);
                        strReplace$default = StringsKt.replace$default(strReplace$default, it, Intrinsics.stringPlus("../", href), false, 4, (Object) null);
                    }
                }
            }
            data.append(strReplace$default).append("\n");
        }
        String string = data.toString();
        Intrinsics.checkNotNullExpressionValue(string, "data.toString()");
        return string;
    }

    private final void setEpubMetadata(Book book, EpubBook epubBook) {
        me.ag2s.epublib.domain.Metadata metadata = new me.ag2s.epublib.domain.Metadata();
        metadata.getTitles().add(book.getName());
        metadata.getAuthors().add(new Author(book.getRealAuthor()));
        metadata.setLanguage("zh");
        metadata.getDates().add(new Date());
        metadata.getPublishers().add("Legado");
        metadata.getDescriptions().add(book.getDisplayIntro());
        epubBook.setMetadata(metadata);
    }

    /* JADX WARN: Code restructure failed: missing block: B:105:0x04db, code lost:
    
        if (r28 < r29) goto L106;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x04de, code lost:
    
        r0 = r28;
        r28 = r28 + 1;
        r27 = r0;
        r0 = (io.legado.app.data.entities.BookChapter) r24.get(r0);
        r34.L$0 = r13;
        r34.L$1 = r16;
        r34.L$2 = r18;
        r34.L$3 = r22;
        r34.L$4 = r24;
        r34.L$5 = r25;
        r34.L$6 = r26;
        r34.I$0 = r20;
        r34.I$1 = r27;
        r34.I$2 = r28;
        r34.I$3 = r29;
        r34.label = 4;
        r0 = r13.searchChapter(r22, r0, r18, r34);
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0557, code lost:
    
        if (r0 != r0) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x055c, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x05fb, code lost:
    
        if (r28 < r29) goto L106;
     */
    /* JADX WARN: Path cross not found for [B:84:0x03a5, B:86:0x03af], limit reached: 124 */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0478  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0480  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x03b7  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x03cb  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x03d1  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0420  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:120:0x05fb -> B:106:0x04de). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object searchBookContent(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r14, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1591
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.searchBookContent(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* renamed from: searchBookContent$lambda-30, reason: not valid java name */
    private static final void m837searchBookContent$lambda30(Ref.BooleanRef isEnd, BookController this$0, Void it) {
        Intrinsics.checkNotNullParameter(isEnd, "$isEnd");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BookControllerKt.logger.info("客户端已断开链接，停止 searchBookContent");
        isEnd.element = true;
        JobKt__JobKt.cancel$default(this$0.getCoroutineContext(), (CancellationException) null, 1, (Object) null);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object searchChapter(@org.jetbrains.annotations.NotNull io.legado.app.data.entities.Book r16, @org.jetbrains.annotations.NotNull io.legado.app.data.entities.BookChapter r17, @org.jetbrains.annotations.NotNull java.lang.String r18, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<io.legado.app.data.entities.SearchResult>> r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 428
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.searchChapter(io.legado.app.data.entities.Book, io.legado.app.data.entities.BookChapter, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object searchPosition(String mContent, String pattern, Continuation<? super List<Integer>> $completion) {
        List position = new ArrayList();
        int index = StringsKt.indexOf$default((CharSequence) mContent, pattern, 0, false, 6, (Object) null);
        if (index >= 0) {
            while (index >= 0) {
                position.add(Boxing.boxInt(index));
                index = StringsKt.indexOf$default((CharSequence) mContent, pattern, index + 1, false, 4, (Object) null);
            }
        }
        return position;
    }

    private final Pair<Integer, String> getResultAndQueryIndex(String content, int queryIndexInContent, String query) {
        int po1 = queryIndexInContent - 20;
        int po2 = queryIndexInContent + query.length() + 20;
        if (po1 < 0) {
            po1 = 0;
        }
        if (po2 > content.length()) {
            po2 = content.length();
        }
        int queryIndexInResult = queryIndexInContent - po1;
        if (content == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String newText = content.substring(po1, po2);
        Intrinsics.checkNotNullExpressionValue(newText, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return TuplesKt.to(Integer.valueOf(queryIndexInResult), newText);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object backupToMongodb(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 628
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.backupToMongodb(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object restoreFromMongodb(@org.jetbrains.annotations.NotNull io.vertx.ext.web.RoutingContext r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.htmake.reader.api.ReturnData> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 621
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htmake.reader.api.controller.BookController.restoreFromMongodb(io.vertx.ext.web.RoutingContext, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
