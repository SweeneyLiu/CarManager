package com.lsw.carmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lsw.carmanager.R;
import com.lsw.carmanager.algorithm.HeadSort;
import com.lsw.carmanager.algorithm.Idea;
import com.lsw.carmanager.fourier.FFT;
import com.lsw.carmanager.huffman.HuffmanCompress;
import com.lsw.carmanager.utils.DeviceInfoUtils;
import com.lsw.carmanager.utils.FileUtils;

import org.bouncycastle.util.encoders.Hex;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.charset.Charset;

public class MainActivity2 extends AppCompatActivity {

    private Handler mHandler;
    private Button intTestButton;
    private static TextView intTestResult;
    private Button floatTestButton;
    private static TextView floatTestResult;
    private Button romTestButton;
    private Button ramTestButton;
    private static TextView ramTestResult;

    private static final String TAG = "MainActivity2";
    private TextView cpuNums;
    private Button netSpeedButton;
    private long cpuIntRunTimes = 10000;
    private long cpuFloatRunTimes = 100;
    private long ramRunTimes = 10000;
    private TextView ramSize;
    private TextView romSize;
    private String readFile;
    private File writeFile;
    //    private String readSmallFile;
    private String readBigFile;
    private static TextView romWriteResult;
    private static TextView romReadResult;
    private String content = "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest";
    private Button gpuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        mHandler = new UiHandler(this);
        writeFile = new File(getExternalCacheDir().getPath() + "/romtest/write.txt");
//        readSmallFile = getExternalCacheDir().getPath() + "/romtest/small_file.txt";
        readBigFile = getExternalCacheDir().getPath() + "/romtest/big_file.txt";
    }

    private void executeHeadSort() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100};
        HeadSort.sort(arr);
    }

    private void executeIdea() {
        byte[] data = "车机管家".getBytes(Charset.forName("UTF-8"));
        byte[] key = Idea.getDesKey();
        String hexKeyStr = Hex.toHexString(key);
        System.out.println("加密密钥:" + hexKeyStr + ",length=" + hexKeyStr.length());
        byte[] encryptData = Idea.encryptIdea(data, key);
        System.out.println("加密数据:" + Hex.toHexString(encryptData));
        byte[] decryptData = Idea.decryptIdea(encryptData, key);
        System.out.println("解密数据:" + new String(decryptData));
    }

    private void executeHuffman() {
        HuffmanCompress sample = new HuffmanCompress();
        File inputFile = new File(Environment.getExternalStorageDirectory().getPath() + "cm/" + "test.txt");
        if (inputFile.exists()) {
            Log.i(TAG, "inputFile: true");
        } else {
            Log.i(TAG, "inputFile: false");
        }
        File outputFile = new File(Environment.getExternalStorageDirectory().getPath() + "cm/" + "test.rar");
        sample.compress(inputFile, outputFile);
    }

    private void initView() {
        intTestButton = (Button) findViewById(R.id.int_test_button);
        intTestResult = (TextView) findViewById(R.id.int_test_result);
        floatTestButton = (Button) findViewById(R.id.float_test_button);
        floatTestResult = (TextView) findViewById(R.id.float_test_result);
        romTestButton = (Button) findViewById(R.id.rom_test_button);
        ramTestButton = (Button) findViewById(R.id.ram_test_button);
        ramTestResult = (TextView) findViewById(R.id.ram_test_result);

        intTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long startTime = System.currentTimeMillis();
                for (int i = 0; i < cpuIntRunTimes; i++) {
                    executeHeadSort();
                }
//                executeHuffman();
                long time = System.currentTimeMillis() - startTime;
                Message message = new Message();
                message.what = 1;
                message.obj = time;
                mHandler.sendMessage(message);
            }
        });

        floatTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long startTime = System.currentTimeMillis();
                for (int i = 0; i < cpuFloatRunTimes; i++) {
                    FFT.FourierCalculate();
                }
                long time = System.currentTimeMillis() - startTime;
                Log.i(TAG, "onClick: time = " + time);
                Message message = new Message();
                message.what = 2;
                message.obj = time;
                mHandler.sendMessage(message);
            }
        });

        romTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        FileUtils.delFile(writeFile, true);
                        long prevTime = System.currentTimeMillis();
                        try {
                            FileUtils.appendToFile(content, writeFile);//content大小4kb
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        long diff = System.currentTimeMillis() - prevTime;
                        Log.i(TAG, "read: diff = " + diff);
                        Message message = new Message();
                        message.what = 3;
                        message.obj = (1000 * 4) / diff;
                        mHandler.sendMessage(message);
                    }
                }).start();
                //读取速度
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long prevTime = System.currentTimeMillis();
                        FileUtils.read(readBigFile);//文件大小4kb
                        long diff = System.currentTimeMillis() - prevTime;
                        Log.i(TAG, "read: diff = " + diff);
                        Message message = new Message();
                        message.what = 4;
                        message.obj = (1000 * 4) / diff;
                        mHandler.sendMessage(message);
                    }
                }).start();
            }
        });

        ramTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long startTime = System.currentTimeMillis();
                for (int i = 0; i < ramRunTimes; i++) {
                    setMatrixData();
                }
                long time = System.currentTimeMillis() - startTime;
                Log.i(TAG, "onClick: time = " + time);
                Message message = new Message();
                message.what = 5;
                if (time != 0) {
                    message.obj = (long) (ramRunTimes * 1000 / time);
                } else {
                    message.obj = (long) 0;
                }
                mHandler.sendMessage(message);
            }
        });

        cpuNums = (TextView) findViewById(R.id.cpu_num);
        String cpus = "CPU核心数：" + DeviceInfoUtils.getNumCores();
        cpuNums.setText(cpus);
        netSpeedButton = (Button) findViewById(R.id.net_speed_button);
        netSpeedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, SpeedActivity.class);
                startActivity(intent);
            }
        });
        ramSize = (TextView) findViewById(R.id.ram_size);
        ramSize.setText("RAM总大小：" + DeviceInfoUtils.getTotalRam() + "\nRAM剩余：" + DeviceInfoUtils.getAvailableRam(this) + "MB");
        romSize = (TextView) findViewById(R.id.rom_size);
        romSize.setText("ROM总大小：" + DeviceInfoUtils.getTotalROM() + "MB" + "\nROM剩余：" + DeviceInfoUtils.getAvailableROM() + "MB");
        romWriteResult = (TextView) findViewById(R.id.rom_write_result);
        romReadResult = (TextView) findViewById(R.id.rom_read_result);

        gpuButton = (Button) findViewById(R.id.gpu_button);
        gpuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, GPUActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);//传null,移除所有callback和messages
    }

    private void setMatrixData() {
        // 定义多维数组
        int[][] k = new int[4][5];
        //利用嵌套循环赋值
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                k[i][j] = (int) (Math.random() * 100);                              //  产生随机数。
            }
        }

    }

    private static class UiHandler extends Handler {

        private WeakReference<MainActivity2> activityWeakReference;

        public UiHandler(MainActivity2 activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity2 activity = activityWeakReference.get();
            if (activity != null) {
                if (msg.what == 1) {
                    //CPU整点计算
                    long result = (long) msg.obj;
                    intTestResult.setText("CPU整点计算耗时：" + result + "ms");
                } else if (msg.what == 2) {
                    //CPU浮点计算
                    long result = (long) msg.obj;
                    floatTestResult.setText("CPU浮点计算耗时：" + result + "ms");
                } else if (msg.what == 3) {
                    //ROM计算
                    long result = (long) msg.obj;
                    romWriteResult.setText("ROM写入速度：" + result + "KB/S");
                } else if (msg.what == 4) {
                    long result = (long) msg.obj;
                    romReadResult.setText("ROM读取速度：" + result + "KB/S");
                } else if (msg.what == 5) {
                    long result = (long) msg.obj;
                    ramTestResult.setText("RAM运行速度：" + result + "次/秒");
                }
            }
        }
    }


}
