package me.ag2s.epublib.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Date implements Serializable {
   private static final long serialVersionUID = 7533866830395120136L;
   private Date.Event event;
   private String dateString;

   public Date() {
      this(new java.util.Date(), Date.Event.CREATION);
   }

   public Date(java.util.Date date) {
      this(date, (Date.Event)null);
   }

   public Date(String dateString) {
      this(dateString, (Date.Event)null);
   }

   public Date(java.util.Date date, Date.Event event) {
      this((new SimpleDateFormat("yyyy-MM-dd", Locale.US)).format(date), event);
   }

   public Date(String dateString, Date.Event event) {
      this.dateString = dateString;
      this.event = event;
   }

   public Date(java.util.Date date, String event) {
      this((new SimpleDateFormat("yyyy-MM-dd", Locale.US)).format(date), event);
   }

   public Date(String dateString, String event) {
      this(checkDate(dateString), Date.Event.fromValue(event));
      this.dateString = dateString;
   }

   private static String checkDate(String dateString) {
      if (dateString == null) {
         throw new IllegalArgumentException("Cannot create a date from a blank string");
      } else {
         return dateString;
      }
   }

   public String getValue() {
      return this.dateString;
   }

   public Date.Event getEvent() {
      return this.event;
   }

   public void setEvent(Date.Event event) {
      this.event = event;
   }

   public String toString() {
      return this.event == null ? this.dateString : "" + this.event + ":" + this.dateString;
   }

   public static enum Event {
      PUBLICATION("publication"),
      MODIFICATION("modification"),
      CREATION("creation");

      private final String value;

      private Event(String v) {
         this.value = v;
      }

      public static Date.Event fromValue(String v) {
         Date.Event[] var1 = values();
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            Date.Event c = var1[var3];
            if (c.value.equals(v)) {
               return c;
            }
         }

         return null;
      }

      public String toString() {
         return this.value;
      }
   }
}
