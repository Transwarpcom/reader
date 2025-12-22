package io.legado.app.model.analyzeRule;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

public class QueryTTF {
   private final QueryTTF.ByteArrayReader fontReader;
   private final QueryTTF.Header fileHeader = new QueryTTF.Header();
   private final List<QueryTTF.Directory> directorys = new LinkedList();
   private final QueryTTF.NameLayout name = new QueryTTF.NameLayout();
   private final QueryTTF.HeadLayout head = new QueryTTF.HeadLayout();
   private final QueryTTF.MaxpLayout maxp = new QueryTTF.MaxpLayout();
   private final List<Integer> loca = new LinkedList();
   private final QueryTTF.CmapLayout Cmap = new QueryTTF.CmapLayout();
   private final List<QueryTTF.GlyfLayout> glyf = new LinkedList();
   private final Pair<Integer, Integer>[] pps = new Pair[]{Pair.of(3, 10), Pair.of(0, 4), Pair.of(3, 1), Pair.of(1, 0), Pair.of(0, 3), Pair.of(0, 1)};
   public final Map<Integer, String> codeToGlyph = new HashMap();
   public final Map<String, Integer> glyphToCode = new HashMap();
   private int limitMix = 0;
   private int limitMax = 0;

   public QueryTTF(byte[] buffer) {
      this.fontReader = new QueryTTF.ByteArrayReader(buffer, 0);
      this.fileHeader.majorVersion = this.fontReader.ReadUInt16();
      this.fileHeader.minorVersion = this.fontReader.ReadUInt16();
      this.fileHeader.numOfTables = this.fontReader.ReadUInt16();
      this.fileHeader.searchRange = this.fontReader.ReadUInt16();
      this.fileHeader.entrySelector = this.fontReader.ReadUInt16();
      this.fileHeader.rangeShift = this.fontReader.ReadUInt16();

      int key;
      QueryTTF.Directory Temp;
      for(key = 0; key < this.fileHeader.numOfTables; ++key) {
         Temp = new QueryTTF.Directory();
         Temp.tag = this.fontReader.ReadStrings(4, StandardCharsets.US_ASCII);
         Temp.checkSum = this.fontReader.ReadUInt32();
         Temp.offset = this.fontReader.ReadUInt32();
         Temp.length = this.fontReader.ReadUInt32();
         this.directorys.add(Temp);
      }

      Iterator var10 = this.directorys.iterator();

      while(true) {
         int i;
         do {
            if (!var10.hasNext()) {
               var10 = this.directorys.iterator();

               while(var10.hasNext()) {
                  Temp = (QueryTTF.Directory)var10.next();
                  if (Temp.tag.equals("head")) {
                     this.fontReader.index = Temp.offset;
                     this.head.majorVersion = this.fontReader.ReadUInt16();
                     this.head.minorVersion = this.fontReader.ReadUInt16();
                     this.head.fontRevision = this.fontReader.ReadUInt32();
                     this.head.checkSumAdjustment = this.fontReader.ReadUInt32();
                     this.head.magicNumber = this.fontReader.ReadUInt32();
                     this.head.flags = this.fontReader.ReadUInt16();
                     this.head.unitsPerEm = this.fontReader.ReadUInt16();
                     this.head.created = this.fontReader.ReadUInt64();
                     this.head.modified = this.fontReader.ReadUInt64();
                     this.head.xMin = this.fontReader.ReadInt16();
                     this.head.yMin = this.fontReader.ReadInt16();
                     this.head.xMax = this.fontReader.ReadInt16();
                     this.head.yMax = this.fontReader.ReadInt16();
                     this.head.macStyle = this.fontReader.ReadUInt16();
                     this.head.lowestRecPPEM = this.fontReader.ReadUInt16();
                     this.head.fontDirectionHint = this.fontReader.ReadInt16();
                     this.head.indexToLocFormat = this.fontReader.ReadInt16();
                     this.head.glyphDataFormat = this.fontReader.ReadInt16();
                  }
               }

               var10 = this.directorys.iterator();

               while(var10.hasNext()) {
                  Temp = (QueryTTF.Directory)var10.next();
                  if (Temp.tag.equals("maxp")) {
                     this.fontReader.index = Temp.offset;
                     this.maxp.majorVersion = this.fontReader.ReadUInt16();
                     this.maxp.minorVersion = this.fontReader.ReadUInt16();
                     this.maxp.numGlyphs = this.fontReader.ReadUInt16();
                     this.maxp.maxPoints = this.fontReader.ReadUInt16();
                     this.maxp.maxContours = this.fontReader.ReadUInt16();
                     this.maxp.maxCompositePoints = this.fontReader.ReadUInt16();
                     this.maxp.maxCompositeContours = this.fontReader.ReadUInt16();
                     this.maxp.maxZones = this.fontReader.ReadUInt16();
                     this.maxp.maxTwilightPoints = this.fontReader.ReadUInt16();
                     this.maxp.maxStorage = this.fontReader.ReadUInt16();
                     this.maxp.maxFunctionDefs = this.fontReader.ReadUInt16();
                     this.maxp.maxInstructionDefs = this.fontReader.ReadUInt16();
                     this.maxp.maxStackElements = this.fontReader.ReadUInt16();
                     this.maxp.maxSizeOfInstructions = this.fontReader.ReadUInt16();
                     this.maxp.maxComponentElements = this.fontReader.ReadUInt16();
                     this.maxp.maxComponentDepth = this.fontReader.ReadUInt16();
                  }
               }

               var10 = this.directorys.iterator();

               while(true) {
                  do {
                     if (!var10.hasNext()) {
                        var10 = this.directorys.iterator();

                        while(true) {
                           int EndIndex;
                           int flagLength;
                           int n;
                           do {
                              if (!var10.hasNext()) {
                                 var10 = this.directorys.iterator();

                                 while(true) {
                                    do {
                                       if (!var10.hasNext()) {
                                          for(key = 0; key < 130000; ++key) {
                                             if (key == 255) {
                                                key = 13312;
                                             }

                                             int gid = this.getGlyfIndex(key);
                                             if (gid != 0) {
                                                StringBuilder sb = new StringBuilder();
                                                short[] var18 = ((QueryTTF.GlyfLayout)this.glyf.get(gid)).xCoordinates;
                                                EndIndex = var18.length;

                                                short b;
                                                for(flagLength = 0; flagLength < EndIndex; ++flagLength) {
                                                   b = var18[flagLength];
                                                   sb.append(b);
                                                }

                                                var18 = ((QueryTTF.GlyfLayout)this.glyf.get(gid)).yCoordinates;
                                                EndIndex = var18.length;

                                                for(flagLength = 0; flagLength < EndIndex; ++flagLength) {
                                                   b = var18[flagLength];
                                                   sb.append(b);
                                                }

                                                String val = sb.toString();
                                                if (this.limitMix == 0) {
                                                   this.limitMix = key;
                                                }

                                                this.limitMax = key;
                                                this.codeToGlyph.put(key, val);
                                                if (!this.glyphToCode.containsKey(val)) {
                                                   this.glyphToCode.put(val, key);
                                                }
                                             }
                                          }

                                          return;
                                       }

                                       Temp = (QueryTTF.Directory)var10.next();
                                    } while(!Temp.tag.equals("glyf"));

                                    this.fontReader.index = Temp.offset;

                                    for(i = 0; i < this.maxp.numGlyphs; ++i) {
                                       this.fontReader.index = Temp.offset + (Integer)this.loca.get(i);
                                       short numberOfContours = this.fontReader.ReadInt16();
                                       if (numberOfContours > 0) {
                                          QueryTTF.GlyfLayout g = new QueryTTF.GlyfLayout();
                                          g.numberOfContours = numberOfContours;
                                          g.xMin = this.fontReader.ReadInt16();
                                          g.yMin = this.fontReader.ReadInt16();
                                          g.xMax = this.fontReader.ReadInt16();
                                          g.yMax = this.fontReader.ReadInt16();
                                          g.endPtsOfContours = this.fontReader.GetUInt16Array(numberOfContours);
                                          g.instructionLength = this.fontReader.ReadUInt16();
                                          g.instructions = this.fontReader.GetBytes(g.instructionLength);
                                          flagLength = g.endPtsOfContours[g.endPtsOfContours.length - 1] + 1;
                                          g.flags = new byte[flagLength];

                                          int n;
                                          for(n = 0; n < flagLength; ++n) {
                                             g.flags[n] = this.fontReader.GetByte();
                                             if ((g.flags[n] & 8) != 0) {
                                                for(n = this.fontReader.ReadUInt8(); n > 0; --n) {
                                                   ++n;
                                                   g.flags[n] = g.flags[n - 1];
                                                }
                                             }
                                          }

                                          g.xCoordinates = new short[flagLength];

                                          short same;
                                          for(n = 0; n < flagLength; ++n) {
                                             same = (short)((g.flags[n] & 16) != 0 ? 1 : -1);
                                             if ((g.flags[n] & 2) != 0) {
                                                g.xCoordinates[n] = (short)(same * this.fontReader.ReadUInt8());
                                             } else {
                                                g.xCoordinates[n] = same == 1 ? 0 : this.fontReader.ReadInt16();
                                             }
                                          }

                                          g.yCoordinates = new short[flagLength];

                                          for(n = 0; n < flagLength; ++n) {
                                             same = (short)((g.flags[n] & 32) != 0 ? 1 : -1);
                                             if ((g.flags[n] & 4) != 0) {
                                                g.yCoordinates[n] = (short)(same * this.fontReader.ReadUInt8());
                                             } else {
                                                g.yCoordinates[n] = same == 1 ? 0 : this.fontReader.ReadInt16();
                                             }
                                          }

                                          this.glyf.add(g);
                                       }
                                    }
                                 }
                              }

                              Temp = (QueryTTF.Directory)var10.next();
                           } while(!Temp.tag.equals("cmap"));

                           this.fontReader.index = Temp.offset;
                           this.Cmap.version = this.fontReader.ReadUInt16();
                           this.Cmap.numTables = this.fontReader.ReadUInt16();

                           for(i = 0; i < this.Cmap.numTables; ++i) {
                              QueryTTF.CmapRecord record = new QueryTTF.CmapRecord();
                              record.platformID = this.fontReader.ReadUInt16();
                              record.encodingID = this.fontReader.ReadUInt16();
                              record.offset = this.fontReader.ReadUInt32();
                              this.Cmap.records.add(record);
                           }

                           for(i = 0; i < this.Cmap.numTables; ++i) {
                              int fmtOffset = ((QueryTTF.CmapRecord)this.Cmap.records.get(i)).offset;
                              this.fontReader.index = Temp.offset + fmtOffset;
                              EndIndex = this.fontReader.index;
                              flagLength = this.fontReader.ReadUInt16();
                              if (!this.Cmap.tables.containsKey(fmtOffset)) {
                                 if (flagLength == 0) {
                                    QueryTTF.CmapFormat f = new QueryTTF.CmapFormat();
                                    f.format = flagLength;
                                    f.length = this.fontReader.ReadUInt16();
                                    f.language = this.fontReader.ReadUInt16();
                                    f.glyphIdArray = this.fontReader.GetBytes(f.length - 6);
                                    this.Cmap.tables.put(fmtOffset, f);
                                 } else if (flagLength == 4) {
                                    QueryTTF.CmapFormat4 f = new QueryTTF.CmapFormat4();
                                    f.format = flagLength;
                                    f.length = this.fontReader.ReadUInt16();
                                    f.language = this.fontReader.ReadUInt16();
                                    f.segCountX2 = this.fontReader.ReadUInt16();
                                    n = f.segCountX2 >> 1;
                                    f.searchRange = this.fontReader.ReadUInt16();
                                    f.entrySelector = this.fontReader.ReadUInt16();
                                    f.rangeShift = this.fontReader.ReadUInt16();
                                    f.endCode = this.fontReader.GetUInt16Array(n);
                                    f.reservedPad = this.fontReader.ReadUInt16();
                                    f.startCode = this.fontReader.GetUInt16Array(n);
                                    f.idDelta = this.fontReader.GetInt16Array(n);
                                    f.idRangeOffset = this.fontReader.GetUInt16Array(n);
                                    f.glyphIdArray = this.fontReader.GetUInt16Array(EndIndex + f.length - this.fontReader.index >> 1);
                                    this.Cmap.tables.put(fmtOffset, f);
                                 } else if (flagLength == 6) {
                                    QueryTTF.CmapFormat6 f = new QueryTTF.CmapFormat6();
                                    f.format = flagLength;
                                    f.length = this.fontReader.ReadUInt16();
                                    f.language = this.fontReader.ReadUInt16();
                                    f.firstCode = this.fontReader.ReadUInt16();
                                    f.entryCount = this.fontReader.ReadUInt16();
                                    f.glyphIdArray = this.fontReader.GetUInt16Array(f.entryCount);
                                    this.Cmap.tables.put(fmtOffset, f);
                                 } else if (flagLength == 12) {
                                    QueryTTF.CmapFormat12 f = new QueryTTF.CmapFormat12();
                                    f.format = flagLength;
                                    f.reserved = this.fontReader.ReadUInt16();
                                    f.length = this.fontReader.ReadUInt32();
                                    f.language = this.fontReader.ReadUInt32();
                                    f.numGroups = this.fontReader.ReadUInt32();
                                    f.groups = new ArrayList(f.numGroups);

                                    for(n = 0; n < f.numGroups; ++n) {
                                       f.groups.add(Triple.of(this.fontReader.ReadUInt32(), this.fontReader.ReadUInt32(), this.fontReader.ReadUInt32()));
                                    }

                                    this.Cmap.tables.put(fmtOffset, f);
                                 }
                              }
                           }
                        }
                     }

                     Temp = (QueryTTF.Directory)var10.next();
                  } while(!Temp.tag.equals("loca"));

                  this.fontReader.index = Temp.offset;
                  i = this.head.indexToLocFormat == 0 ? 2 : 4;

                  for(long i = 0L; i < (long)Temp.length; i += (long)i) {
                     this.loca.add(i == 2 ? this.fontReader.ReadUInt16() << 1 : this.fontReader.ReadUInt32());
                  }
               }
            }

            Temp = (QueryTTF.Directory)var10.next();
         } while(!Temp.tag.equals("name"));

         this.fontReader.index = Temp.offset;
         this.name.format = this.fontReader.ReadUInt16();
         this.name.count = this.fontReader.ReadUInt16();
         this.name.stringOffset = this.fontReader.ReadUInt16();

         for(i = 0; i < this.name.count; ++i) {
            QueryTTF.NameRecord record = new QueryTTF.NameRecord();
            record.platformID = this.fontReader.ReadUInt16();
            record.encodingID = this.fontReader.ReadUInt16();
            record.languageID = this.fontReader.ReadUInt16();
            record.nameID = this.fontReader.ReadUInt16();
            record.length = this.fontReader.ReadUInt16();
            record.offset = this.fontReader.ReadUInt16();
            this.name.records.add(record);
         }
      }
   }

   public String getNameById(int nameId) {
      Iterator var2 = this.directorys.iterator();

      while(var2.hasNext()) {
         QueryTTF.Directory Temp = (QueryTTF.Directory)var2.next();
         if (Temp.tag.equals("name")) {
            this.fontReader.index = Temp.offset;
            break;
         }
      }

      var2 = this.name.records.iterator();

      QueryTTF.NameRecord record;
      do {
         if (!var2.hasNext()) {
            return "error";
         }

         record = (QueryTTF.NameRecord)var2.next();
      } while(record.nameID != nameId);

      QueryTTF.ByteArrayReader var10000 = this.fontReader;
      var10000.index += this.name.stringOffset + record.offset;
      return this.fontReader.ReadStrings(record.length, record.platformID == 1 ? StandardCharsets.UTF_8 : StandardCharsets.UTF_16BE);
   }

   private int getGlyfIndex(int code) {
      if (code == 0) {
         return 0;
      } else {
         int fmtKey = 0;
         Pair[] var3 = this.pps;
         int var4 = var3.length;

         int fmt;
         for(fmt = 0; fmt < var4; ++fmt) {
            Pair<Integer, Integer> item = var3[fmt];
            Iterator var7 = this.Cmap.records.iterator();

            while(var7.hasNext()) {
               QueryTTF.CmapRecord record = (QueryTTF.CmapRecord)var7.next();
               if ((Integer)item.getLeft() == record.platformID && (Integer)item.getRight() == record.encodingID) {
                  fmtKey = record.offset;
                  break;
               }
            }

            if (fmtKey > 0) {
               break;
            }
         }

         if (fmtKey == 0) {
            return 0;
         } else {
            int glyfID = 0;
            QueryTTF.CmapFormat table = (QueryTTF.CmapFormat)this.Cmap.tables.get(fmtKey);

            assert table != null;

            fmt = table.format;
            if (fmt == 0) {
               if (code < table.glyphIdArray.length) {
                  glyfID = table.glyphIdArray[code] & 255;
               }
            } else {
               int end;
               int start;
               int middle;
               if (fmt == 4) {
                  QueryTTF.CmapFormat4 tab = (QueryTTF.CmapFormat4)table;
                  if (code > tab.endCode[tab.endCode.length - 1]) {
                     return 0;
                  }

                  start = 0;
                  end = tab.endCode.length - 1;

                  while(start + 1 < end) {
                     middle = (start + end) / 2;
                     if (tab.endCode[middle] <= code) {
                        start = middle;
                     } else {
                        end = middle;
                     }
                  }

                  if (tab.endCode[start] < code) {
                     ++start;
                  }

                  if (code < tab.startCode[start]) {
                     return 0;
                  }

                  if (tab.idRangeOffset[start] != 0) {
                     glyfID = tab.glyphIdArray[code - tab.startCode[start] + (tab.idRangeOffset[start] >> 1) - (tab.idRangeOffset.length - start)];
                  } else {
                     glyfID = code + tab.idDelta[start];
                  }

                  glyfID &= 65535;
               } else if (fmt == 6) {
                  QueryTTF.CmapFormat6 tab = (QueryTTF.CmapFormat6)table;
                  start = code - tab.firstCode;
                  if (start >= 0 && start < tab.glyphIdArray.length) {
                     glyfID = tab.glyphIdArray[start];
                  } else {
                     glyfID = 0;
                  }
               } else if (fmt == 12) {
                  QueryTTF.CmapFormat12 tab = (QueryTTF.CmapFormat12)table;
                  if (code > (Integer)((Triple)tab.groups.get(tab.numGroups - 1)).getMiddle()) {
                     return 0;
                  }

                  start = 0;
                  end = tab.numGroups - 1;

                  while(start + 1 < end) {
                     middle = (start + end) / 2;
                     if ((Integer)((Triple)tab.groups.get(middle)).getLeft() <= code) {
                        start = middle;
                     } else {
                        end = middle;
                     }
                  }

                  if ((Integer)((Triple)tab.groups.get(start)).getLeft() <= code && code <= (Integer)((Triple)tab.groups.get(start)).getMiddle()) {
                     glyfID = (Integer)((Triple)tab.groups.get(start)).getRight() + code - (Integer)((Triple)tab.groups.get(start)).getLeft();
                  }
               }
            }

            return glyfID;
         }
      }
   }

   public boolean inLimit(char code) {
      return this.limitMix <= code && code < this.limitMax;
   }

   public String getGlyfByCode(int key) {
      return (String)this.codeToGlyph.getOrDefault(key, "");
   }

   public int getCodeByGlyf(String val) {
      return (Integer)this.glyphToCode.getOrDefault(val, 0);
   }

   private static class ByteArrayReader {
      public int index;
      public byte[] buffer;

      public ByteArrayReader(byte[] buffer, int index) {
         this.buffer = buffer;
         this.index = index;
      }

      public long ReadUIntX(long len) {
         long result = 0L;

         for(long i = 0L; i < len; ++i) {
            result <<= 8;
            result |= (long)(this.buffer[this.index++] & 255);
         }

         return result;
      }

      public long ReadUInt64() {
         return this.ReadUIntX(8L);
      }

      public int ReadUInt32() {
         return (int)this.ReadUIntX(4L);
      }

      public int ReadUInt16() {
         return (int)this.ReadUIntX(2L);
      }

      public short ReadInt16() {
         return (short)((int)this.ReadUIntX(2L));
      }

      public short ReadUInt8() {
         return (short)((int)this.ReadUIntX(1L));
      }

      public String ReadStrings(int len, Charset charset) {
         byte[] result = len > 0 ? new byte[len] : null;

         for(int i = 0; i < len; ++i) {
            result[i] = this.buffer[this.index++];
         }

         return new String(result, charset);
      }

      public byte GetByte() {
         return this.buffer[this.index++];
      }

      public byte[] GetBytes(int len) {
         byte[] result = len > 0 ? new byte[len] : null;

         for(int i = 0; i < len; ++i) {
            result[i] = this.buffer[this.index++];
         }

         return result;
      }

      public int[] GetUInt16Array(int len) {
         int[] result = len > 0 ? new int[len] : null;

         for(int i = 0; i < len; ++i) {
            result[i] = this.ReadUInt16();
         }

         return result;
      }

      public short[] GetInt16Array(int len) {
         short[] result = len > 0 ? new short[len] : null;

         for(int i = 0; i < len; ++i) {
            result[i] = this.ReadInt16();
         }

         return result;
      }
   }

   private static class GlyfLayout {
      public short numberOfContours;
      public short xMin;
      public short yMin;
      public short xMax;
      public short yMax;
      public int[] endPtsOfContours;
      public int instructionLength;
      public byte[] instructions;
      public byte[] flags;
      public short[] xCoordinates;
      public short[] yCoordinates;

      private GlyfLayout() {
      }

      // $FF: synthetic method
      GlyfLayout(Object x0) {
         this();
      }
   }

   private static class CmapFormat12 extends QueryTTF.CmapFormat {
      public int reserved;
      public int length;
      public int language;
      public int numGroups;
      public List<Triple<Integer, Integer, Integer>> groups;

      private CmapFormat12() {
         super(null);
      }

      // $FF: synthetic method
      CmapFormat12(Object x0) {
         this();
      }
   }

   private static class CmapFormat6 extends QueryTTF.CmapFormat {
      public int firstCode;
      public int entryCount;
      public int[] glyphIdArray;

      private CmapFormat6() {
         super(null);
      }

      // $FF: synthetic method
      CmapFormat6(Object x0) {
         this();
      }
   }

   private static class CmapFormat4 extends QueryTTF.CmapFormat {
      public int segCountX2;
      public int searchRange;
      public int entrySelector;
      public int rangeShift;
      public int[] endCode;
      public int reservedPad;
      public int[] startCode;
      public short[] idDelta;
      public int[] idRangeOffset;
      public int[] glyphIdArray;

      private CmapFormat4() {
         super(null);
      }

      // $FF: synthetic method
      CmapFormat4(Object x0) {
         this();
      }
   }

   private static class CmapFormat {
      public int format;
      public int length;
      public int language;
      public byte[] glyphIdArray;

      private CmapFormat() {
      }

      // $FF: synthetic method
      CmapFormat(Object x0) {
         this();
      }
   }

   private static class CmapRecord {
      public int platformID;
      public int encodingID;
      public int offset;

      private CmapRecord() {
      }

      // $FF: synthetic method
      CmapRecord(Object x0) {
         this();
      }
   }

   private static class CmapLayout {
      public int version;
      public int numTables;
      public List<QueryTTF.CmapRecord> records;
      public Map<Integer, QueryTTF.CmapFormat> tables;

      private CmapLayout() {
         this.records = new LinkedList();
         this.tables = new HashMap();
      }

      // $FF: synthetic method
      CmapLayout(Object x0) {
         this();
      }
   }

   private static class MaxpLayout {
      public int majorVersion;
      public int minorVersion;
      public int numGlyphs;
      public int maxPoints;
      public int maxContours;
      public int maxCompositePoints;
      public int maxCompositeContours;
      public int maxZones;
      public int maxTwilightPoints;
      public int maxStorage;
      public int maxFunctionDefs;
      public int maxInstructionDefs;
      public int maxStackElements;
      public int maxSizeOfInstructions;
      public int maxComponentElements;
      public int maxComponentDepth;

      private MaxpLayout() {
      }

      // $FF: synthetic method
      MaxpLayout(Object x0) {
         this();
      }
   }

   private static class HeadLayout {
      public int majorVersion;
      public int minorVersion;
      public int fontRevision;
      public int checkSumAdjustment;
      public int magicNumber;
      public int flags;
      public int unitsPerEm;
      public long created;
      public long modified;
      public short xMin;
      public short yMin;
      public short xMax;
      public short yMax;
      public int macStyle;
      public int lowestRecPPEM;
      public short fontDirectionHint;
      public short indexToLocFormat;
      public short glyphDataFormat;

      private HeadLayout() {
      }

      // $FF: synthetic method
      HeadLayout(Object x0) {
         this();
      }
   }

   private static class NameRecord {
      public int platformID;
      public int encodingID;
      public int languageID;
      public int nameID;
      public int length;
      public int offset;

      private NameRecord() {
      }

      // $FF: synthetic method
      NameRecord(Object x0) {
         this();
      }
   }

   private static class NameLayout {
      public int format;
      public int count;
      public int stringOffset;
      public List<QueryTTF.NameRecord> records;

      private NameLayout() {
         this.records = new LinkedList();
      }

      // $FF: synthetic method
      NameLayout(Object x0) {
         this();
      }
   }

   private static class Directory {
      public String tag;
      public int checkSum;
      public int offset;
      public int length;

      private Directory() {
      }

      // $FF: synthetic method
      Directory(Object x0) {
         this();
      }
   }

   private static class Header {
      public int majorVersion;
      public int minorVersion;
      public int numOfTables;
      public int searchRange;
      public int entrySelector;
      public int rangeShift;

      private Header() {
      }

      // $FF: synthetic method
      Header(Object x0) {
         this();
      }
   }
}
