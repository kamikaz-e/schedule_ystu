package com.example.misha.myapplication.data;

import android.provider.BaseColumns;

public final class ScheduleClass {
    private ScheduleClass() {
    }

    public static final class schedule implements BaseColumns {

        public final static String SCHEDULE = "schedule";
        public final static String id = BaseColumns._ID;
        public final static String id_week = "id_week";
        public final static String id_day = "id_day";
        public final static String id_subject = "id_subject";
        public final static String id_audience = "id_audience";
        public final static String id_educator = "id_educator";
        public final static String id_typelesson = "id_typelesson";
    }

    public static final class subjects implements BaseColumns {
        public final static String SUBJECT = "subjects";
        public final static String subject_id="idd_subject";
        public final static String subject = "subject";
    }
    public static final class audiences implements BaseColumns {
        public final static String AUDIENCE = "audiences";
        public final static String audience_id="idd_audience";
        public final static String audience = "audience";
    }
    public static final class educators implements BaseColumns {
        public final static String EDUCATOR = "educators";
        public final static String educator_id = "idd_educator";
        public final static String educator = "educator";
    }
    public static final class typelessons implements BaseColumns {
        public final static String TYPELESSON = "typelessons";
        public final static String typelesson_id = "idd_typelesson";
        public final static String typelesson = "typelesson";
    }
    public static final class calls implements BaseColumns {
        public final static String CALLS = "calls";
        public final static String id_call="id_call";
        public final static String time = "time";
    }
    public static final class date_start implements BaseColumns {
        public final static String DATE_START = "date_start";
        public final static String id_date="id_date";
        public final static String date = "date";
    }
}