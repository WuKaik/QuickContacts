package com.sasincomm.quickcontacts.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 作者：wk on 2018/11/22.
 */
public class Logger {
    private static LogLevel currentLevel= LogLevel.VERBOSE;
    private static boolean isWriter=false;
    private static String pkgName;
    private static String logFilePath;
    private static FileOutputStream fos;
    private static OutputStreamWriter osWriter;
    private static BufferedWriter writer;
    private static final SimpleDateFormat FILE_NAME_FORMAT=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat LOG_TIME_FORMAT=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String LOG_FORMAT="%s";

    /**
     * 日志组件初始化
     * @param appCtx   application 上下文
     * @param isWriter 是否保存文件
     * @param level    日志级别
     */
    public static final void initialize(Context appCtx, boolean isWriter, LogLevel level) {
        currentLevel = level;
        if (level == LogLevel.CLOSE) {
            Logger.isWriter = false;
            return;
        }
        Logger.isWriter = isWriter;
        if (!Logger.isWriter) {//不保存日志到文件
            return;
        }
        String logFoldPath = appCtx.getExternalCacheDir().getAbsolutePath() + "/../log/";
        pkgName = appCtx.getPackageName();
        File logFold = new File(logFoldPath);
        boolean flag = false;
        if (!(flag = logFold.exists()))
            flag = logFold.mkdirs();
        if (!flag) {
            Logger.isWriter = false;
            return;
        }
        logFilePath = logFoldPath + FILE_NAME_FORMAT.format(Calendar.getInstance().getTime()) + "_log.txt";
        Log.d("Logger", "logFilePath: "+logFilePath);
        try {
            File logFile = new File(logFilePath);
            if (!(flag = logFile.exists()))
                flag = logFile.createNewFile();
            Logger.isWriter = isWriter & flag;
            if (Logger.isWriter) {
                fos = new FileOutputStream(logFile);
                osWriter = new OutputStreamWriter(fos);
                writer = new BufferedWriter(osWriter);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Logger.isWriter = false;
        }
    }

    public static final void i(String tag, String msg) {
        if (currentLevel.value > LogLevel.INFO.value)
            return;
        if (isWriter) {
            write(tag, msg, "I");
        }
        Log.i(tag, msg);
    }

    public static final void i(String tag, String msg, Throwable throwable) {
        if (currentLevel.value > LogLevel.INFO.value)
            return;
        if (isWriter) {
            write(tag, msg, "I", throwable);
        }
        Log.i(tag, msg);
    }

    public static final void v(String tag, String msg) {
        if (currentLevel.value > LogLevel.VERBOSE.value)
            return;
        if (isWriter) {
            write(tag, msg, "V");
        }
        Log.v(tag, msg);
    }

    public static final void v(String tag, String msg, Throwable throwable) {
        if (currentLevel.value > LogLevel.VERBOSE.value)
            return;
        if (isWriter) {
            write(tag, msg, "V", throwable);
        }
        Log.v(tag, msg, throwable);
    }

    public static final void d(String tag, String msg) {
        if (currentLevel.value > LogLevel.DEBUG.value)
        {
            return;
        }
        if (isWriter) {
            write(tag, msg, "D");
        }
        Log.d(tag, msg);
    }

    public static final void d(String tag, String msg, Throwable throwable) {
        if (currentLevel.value > LogLevel.DEBUG.value)
            return;
        if (isWriter) {
            write(tag, msg, "D", throwable);
        }
        Log.d(tag, msg, throwable);
    }

    public static final void e(String tag, String msg) {
        if (currentLevel.value > LogLevel.ERROR.value)
            return;
        if (isWriter) {
            write(tag, msg, "E");
        }
        Log.e(tag, msg);
    }

    public static final void e(String tag, String msg, Throwable throwable) {
        if (currentLevel.value > LogLevel.ERROR.value)
            return;
        if (isWriter) {
            write(tag, msg, "E", throwable);
        }
        Log.e(tag, msg, throwable);
    }

    public static final void w(String tag, String msg) {
        if (currentLevel.value > LogLevel.WARN.value)
            return;
        if (isWriter) {
            write(tag, msg, "W");
        }
        Log.w(tag, msg);
    }

    public static final void w(String tag, String msg, Throwable throwable) {
        if (currentLevel.value > LogLevel.WARN.value)
            return;
        if (isWriter) {
            write(tag, msg, "W", throwable);
        }
        Log.w(tag, msg, throwable);
    }

    public static final void i(Object target, String msg) {
        i(target.getClass().getSimpleName(), msg);
    }

    public static final void i(Object target, String msg, Throwable throwable) {
        i(target.getClass().getSimpleName(), msg, throwable);
    }

    public static final void v(Object target, String msg) {
        v(target.getClass().getSimpleName(), msg);
    }

    public static final void v(Object target, String msg, Throwable throwable) {
        v(target.getClass().getSimpleName(), msg, throwable);
    }

    public static final void d(Object target, String msg) {
        d(target.getClass().getSimpleName(), msg);
    }

    public static final void d(Object target, String msg, Throwable throwable) {
        d(target.getClass().getSimpleName(), msg, throwable);
    }

    public static final void e(Object target, String msg) {
        e(target.getClass().getSimpleName(), msg);
    }

    public static final void e(Object target, String msg, Throwable throwable) {
        e(target.getClass().getSimpleName(), msg, throwable);
    }

    public static final void w(Object target, String msg) {
        w(target.getClass().getSimpleName(), msg);
    }

    public static final void w(Object target, String msg, Throwable throwable) {

        w(target.getClass().getSimpleName(), msg, throwable);
    }

    /**
     * 写文件操作
     * @param tag       日志标签
     * @param msg       日志内容
     * @param level     日志级别
     */
    private static final void write(String tag, String msg, String level) {
        String timeStamp = LOG_TIME_FORMAT.format(Calendar.getInstance().getTime());
        try {
            writer.write(String.format(LOG_FORMAT, timeStamp, pkgName, level, tag)+"--");
            writer.write(tag+": ");
            writer.write(msg);
            writer.newLine();
            writer.flush();
            osWriter.flush();
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写文件操作
     * @param tag       日志标签
     * @param msg       日志内容
     * @param level     日志级别
     * @param throwable 异常捕获
     */
    private static final void write(String tag, String msg, String level, Throwable throwable) {
        String timeStamp = LOG_TIME_FORMAT.format(Calendar.getInstance().getTime());
        try {
            writer.write(String.format(LOG_FORMAT, timeStamp, pkgName, level, tag)+"--");
            writer.write(tag+": ");
            writer.write(msg);
            writer.newLine();
            writer.flush();
            osWriter.flush();
            fos.flush();
            if (throwable != null)
                saveCrash(throwable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存异常
     *
     * @param throwable
     * @throws IOException
     */
    private static void saveCrash(Throwable throwable) throws IOException {
        StringWriter sWriter = new StringWriter();
        PrintWriter pWriter = new PrintWriter(sWriter);
        throwable.printStackTrace(pWriter);
        Throwable cause = throwable.getCause();
        while (cause != null) {
            cause.printStackTrace(pWriter);
            cause = cause.getCause();
        }
        pWriter.flush();
        pWriter.close();
        sWriter.flush();
        String crashInfo = writer.toString();
        sWriter.close();
        writer.write(crashInfo);
        writer.newLine();
        writer.flush();
        osWriter.flush();
        fos.flush();
    }

    /**
     * 作者：wk on 2018/11/22.
     */
    public enum LogLevel {
        VERBOSE(Log.VERBOSE),

        DEBUG(Log.DEBUG),

        INFO(Log.INFO),

        WARN(Log.WARN),

        ERROR(Log.ERROR),

        ASSERT(Log.ASSERT),

        CLOSE(Log.ASSERT + 1);

        int value;

        LogLevel(int value) {
            this.value = value;
        }
    }

}
