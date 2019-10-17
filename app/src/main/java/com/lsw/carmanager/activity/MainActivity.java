package com.lsw.carmanager.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lsw.carmanager.R;
import com.lsw.carmanager.algorithm.HeadSort;
import com.lsw.carmanager.fourier.FFT;
import com.lsw.carmanager.utils.DeviceInfoUtils;
import com.lsw.carmanager.utils.FileUtils;
import com.lsw.speedlib.SpeedManager;
import com.lsw.speedlib.listener.NetDelayListener;
import com.lsw.speedlib.listener.SpeedListener;
import com.lsw.speedlib.utils.ConverUtil;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;

import jp.wasabeef.takt.Takt;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView testResult;
    private TextView testDetail;
    private LinearLayout cpu;
    private TextView intTestResult;
    private TextView floatTestResult;
    private LinearLayout rom;
    private TextView romSize;
    private TextView romWriteResult;
    private TextView romReadResult;
    private LinearLayout ram;
    private TextView ramSize;
    private TextView ramTestResult;
    private LinearLayout resultDisplayLayout;

    private File writeFile;
    private Handler mHandler;
    private String readBigFile;

    //运行取均值的次数
    private int detectCpuIntTimes = 10;
    private int detectCpuFloatTimes = 10;
    private int detectRomWriteTimes = 10;
    private int detectRomReadTimes = 10;
    private int detectRamTimes = 5;

    //算法运行的次数
    private long cpuIntRunTimes = 10000;
    private long cpuFloatRunTimes = 50;
    private long romWriteTimes = 256;//每个文件大小4kb,大小刚好1mb
    private long ramRunTimes = 100000;
    private String content = "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest";
    private static int detectNum = 0;

    SpeedManager speedManager;
    private TextView txDelay;
    private TextView txDown;
    private TextView txUp;
    private TextView cpuPerformance;
    private TextView romPerformance;
    private TextView ramPerformance;
    private TextView netSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Takt.play();
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
        startDetect();
    }

    private void initView() {
        testResult = (TextView) findViewById(R.id.test_result);
        testDetail = (TextView) findViewById(R.id.test_detail);
        cpu = (LinearLayout) findViewById(R.id.cpu);
        intTestResult = (TextView) findViewById(R.id.int_test_result);
        floatTestResult = (TextView) findViewById(R.id.float_test_result);
        rom = (LinearLayout) findViewById(R.id.rom);
        romSize = (TextView) findViewById(R.id.rom_size);
        romWriteResult = (TextView) findViewById(R.id.rom_write_result);
        romReadResult = (TextView) findViewById(R.id.rom_read_result);
        ram = (LinearLayout) findViewById(R.id.ram);
        ramSize = (TextView) findViewById(R.id.ram_size);
        ramTestResult = (TextView) findViewById(R.id.ram_test_result);
        resultDisplayLayout = (LinearLayout) findViewById(R.id.result_display_layout);
        txDelay = (TextView) findViewById(R.id.tx_delay);
        txDown = (TextView) findViewById(R.id.tx_down);
        txUp = (TextView) findViewById(R.id.tx_up);
        cpuPerformance = (TextView) findViewById(R.id.cpu_performance);
        romPerformance = (TextView) findViewById(R.id.rom_performance);
        ramPerformance = (TextView) findViewById(R.id.ram_performance);
        netSpeed = (TextView) findViewById(R.id.net_speed);
    }

    private void initData() {
        testResult.setText("测试中");
        mHandler = new UiHandler(this);
        writeFile = new File(getExternalCacheDir().getPath() + "/romtest/write.txt");
        readBigFile = getExternalCacheDir().getPath() + "/romtest/big_file.txt";
    }

    private void startDetect() {
        detectNum = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                detectCpuIntCalculate();
                detectCpuFloatCalculate();
                romWriteSpeed();
                romReadSpeed();
                ramRunSpeed();
            }
        }).start();
    }

    private void detectCpuIntCalculate() {
        long sum = 0;
        for (int i = 0; i < detectCpuIntTimes; i++) {
            long time = getCpuIntRunTime();
            sum += time;
        }
        long averageTime = sum / detectCpuIntTimes;
        postResultMessage(1, averageTime);
    }

    private long getCpuIntRunTime() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < cpuIntRunTimes; i++) {
            executeHeadSort();
        }
        long time = System.currentTimeMillis() - startTime;
        return time;
    }

    private void postResultMessage(int what, Object object) {
        Message message = new Message();
        message.what = what;
        message.obj = object;
        mHandler.sendMessage(message);
    }

    private void executeHeadSort() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100};
        HeadSort.sort(arr);
    }

    private void detectCpuFloatCalculate() {

        long sum = 0;
        for (int i = 0; i < detectCpuFloatTimes; i++) {
            long time = getCpuFloatRunTime();
            sum += time;
        }
        long averageTime = sum / detectCpuFloatTimes;
        postResultMessage(2, averageTime);
    }

    private long getCpuFloatRunTime() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < cpuFloatRunTimes; i++) {
            FFT.FourierCalculate();
        }
        long time = System.currentTimeMillis() - startTime;
        return time;
    }

    private void romWriteSpeed() {

        long sum = 0;
        for (int i = 0; i < detectRomWriteTimes; i++) {
            long time = getRomWriteTime();
            sum += time;
        }
        long averageTime = sum / detectRomWriteTimes;
        postResultMessage(3, averageTime);
    }

    private long getRomWriteTime() {
        FileUtils.delFile(writeFile, true);
        long prevTime = System.currentTimeMillis();
        try {
            for (int i = 0; i < romWriteTimes; i++) {
                FileUtils.appendToFile(content, writeFile);//content大小4kb
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long diff = System.currentTimeMillis() - prevTime;
        long time = 0;
        if (diff != 0) {
            time = (1000 * 1024) / diff;
        }
        return time;
    }

    private void romReadSpeed() {
        long sum = 0;
        for (int i = 0; i < detectRomReadTimes; i++) {
            long time = getRomReadTime();
            sum += time;
        }
        long averageTime = sum / detectRomReadTimes;
        postResultMessage(4, averageTime);

    }

    private long getRomReadTime() {
        long prevTime = System.currentTimeMillis();
        FileUtils.read(readBigFile);//文件大小4kb
        long diff = System.currentTimeMillis() - prevTime;
        Log.i(TAG, "read: diff = " + diff);
        long time = 0;
        if (diff != 0) {
            time = (1000 * 512) / diff;
        }
        return time;
    }

    private void ramRunSpeed() {

        long sum = 0;
        for (int i = 0; i < detectRamTimes; i++) {
            long time = getRamTime();
            sum += time;
        }
        long averageTime = sum / detectRamTimes;
        postResultMessage(5, averageTime);
    }

    private void setMatrixData() {
        // 定义多维数组
        int[][] k = new int[4][5];
        //利用嵌套循环赋值
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                k[i][j] = (int) (Math.random() * 100);//  产生随机数。
            }
        }

    }

    private long getRamTime() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < ramRunTimes; i++) {
            setMatrixData();
        }
        long diff = System.currentTimeMillis() - startTime;
        long time = (long) (ramRunTimes * 1000 / diff);
        Log.i(TAG, "onClick: time = " + time);
        return time;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Takt.finish();
        mHandler.removeCallbacksAndMessages(null);//传null,移除所有callback和messages
    }

    private void detectNetSpeed() {
        speedManager = new SpeedManager.Builder()
                .setNetDelayListener(new NetDelayListener() {
                    @Override
                    public void result(String delay) {
                        txDelay.setText("网络延迟：" + delay);
                    }
                })
                .setSpeedListener(new SpeedListener() {
                    @Override
                    public void speeding(long downSpeed, long upSpeed) {
//                        String[] downResult = ConverUtil.fomartSpeed(downSpeed);
//                        txDown.setText("下载速度："+downResult[0] + downResult[1]);
//                        String[] upResult = ConverUtil.fomartSpeed(upSpeed);
//                        txUp.setText("上传速度："+upResult[0] + upResult[1]);
                    }

                    @Override
                    public void finishSpeed(long finalDownSpeed, long finalUpSpeed) {
                        String[] downResult = ConverUtil.fomartSpeed(finalDownSpeed);
                        txDown.setText("下载速度：" + downResult[0] + downResult[1]);

                        String[] upResult = ConverUtil.fomartSpeed(finalUpSpeed);
                        txUp.setText("上传速度：" + upResult[0] + upResult[1]);
                        netSpeed.setText("网络速度 优");
                        testResult.setText("优");
                    }
                })
                .setPindCmd("202.108.22.5")//202.108.22.5 百度地址
                .setSpeedCount(6)
                .setSpeedTimeOut(2000)
                .builder();
        speedManager.startSpeed();
    }

    private class UiHandler extends Handler {

        private WeakReference<MainActivity> activityWeakReference;

        public UiHandler(MainActivity activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = activityWeakReference.get();
            if (activity != null) {
                if (msg.what == 1) {
                    //CPU整点计算
                    long result = (long) msg.obj;
                    intTestResult.setText("CPU整点计算耗时：" + result + "ms");
                    detectNum = detectNum + msg.what;
                } else if (msg.what == 2) {
                    //CPU浮点计算
                    long result = (long) msg.obj;
                    floatTestResult.setText("CPU浮点计算耗时：" + result + "ms");
                    detectNum = detectNum + msg.what;
                    cpuPerformance.setText("CPU性能 优");
                } else if (msg.what == 3) {
                    romSize.setText("ROM总大小：" + DeviceInfoUtils.getTotalROM() + "MB" + "\nROM剩余：" + DeviceInfoUtils.getAvailableROM() + "MB");
                    //ROM计算
                    long result = (long) msg.obj;
                    romWriteResult.setText("ROM写入速度：" + result + "KB/S");
                    detectNum = detectNum + msg.what;
                } else if (msg.what == 4) {
                    long result = (long) msg.obj;
                    romReadResult.setText("ROM读取速度：" + result + "KB/S");
                    detectNum = detectNum + msg.what;
                    romPerformance.setText("ROM性能 优");
                } else if (msg.what == 5) {
                    ramSize.setText("RAM总大小：" + DeviceInfoUtils.getTotalRam() + "\nRAM剩余：" + DeviceInfoUtils.getAvailableRam(MainActivity.this) + "MB");
                    long result = (long) msg.obj;
                    ramTestResult.setText("RAM运行速度：" + result + "次/秒");
                    detectNum = detectNum + msg.what;
                    ramPerformance.setText("RAM性能 优");
                }

                if (detectNum == 15) {
                    detectNetSpeed();
                }

            }
        }
    }

}
