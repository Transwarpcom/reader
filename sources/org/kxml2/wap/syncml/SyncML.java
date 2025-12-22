package org.kxml2.wap.syncml;

import io.netty.handler.codec.http.cookie.CookieHeaderNames;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.apache.fontbox.afm.AFMParser;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationRubberStamp;
import org.kxml2.wap.WbxmlParser;
import org.kxml2.wap.WbxmlSerializer;

/* loaded from: reader.jar:BOOT-INF/classes/org/kxml2/wap/syncml/SyncML.class */
public abstract class SyncML {
    public static final String[] TAG_TABLE_0 = {"Add", "Alert", "Archive", "Atomic", "Chal", "Cmd", "CmdID", "CmdRef", "Copy", "Cred", "Data", "Delete", "Exec", PDAnnotationRubberStamp.NAME_FINAL, "Get", "Item", "Lang", "LocName", "LocURI", "Map", "MapItem", "Meta", "MsgID", "MsgRef", "NoResp", "NoResults", "Put", "Replace", "RespURI", "Results", "Search", "Sequence", "SessionID", "SftDel", "Source", "SourceRef", "Status", "Sync", "SyncBody", "SyncHdr", "SyncML", "Target", "TargetRef", "Reserved for future use", "VerDTD", "VerProto", "NumberOfChanged", "MoreData", "Field", "Filter", "Record", "FilterType", "SourceParent", "TargetParent", "Move", "Correlator"};
    public static final String[] TAG_TABLE_1 = {"Anchor", "EMI", "Format", "FreeID", "FreeMem", "Last", "Mark", "MaxMsgSize", "Mem", "MetInf", "Next", "NextNonce", "SharedMem", "Size", "Type", AFMParser.VERSION, "MaxObjSize", "FieldLevel"};
    public static final String[] TAG_TABLE_2_DM = {"AccessType", "ACL", "Add", "b64", "bin", "bool", "chr", "CaseSense", "CIS", "Copy", OperatorName.STROKING_COLORSPACE, PackageDocumentBase.DCTags.date, "DDFName", "DefaultValue", "Delete", "Description", "DDFFormat", "DFProperties", "DFTitle", "DFType", "Dynamic", "Exec", "float", "Format", "Get", "int", "Man", "MgmtTree", "MIME", "Mod", "Name", "Node", "node", "NodeName", "null", "Occurence", "One", "OneOrMore", "OneOrN", CookieHeaderNames.PATH, "Permanent", "Replace", "RTProperties", "Scope", "Size", "time", "Title", "TStamp", "Type", "Value", "VerDTD", "VerNo", "xml", "ZeroOrMore", "ZeroOrN", "ZeroOrOne"};

    public static WbxmlParser createParser() {
        WbxmlParser p = new WbxmlParser();
        p.setTagTable(0, TAG_TABLE_0);
        p.setTagTable(1, TAG_TABLE_1);
        return p;
    }

    public static WbxmlSerializer createSerializer() {
        WbxmlSerializer s = new WbxmlSerializer();
        s.setTagTable(0, TAG_TABLE_0);
        s.setTagTable(1, TAG_TABLE_1);
        return s;
    }

    public static WbxmlParser createDMParser() {
        WbxmlParser p = createParser();
        p.setTagTable(2, TAG_TABLE_2_DM);
        return p;
    }

    public static WbxmlSerializer createDMSerializer() {
        WbxmlSerializer s = createSerializer();
        s.setTagTable(2, TAG_TABLE_2_DM);
        return s;
    }
}
