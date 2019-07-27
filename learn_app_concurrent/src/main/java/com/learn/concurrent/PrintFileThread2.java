package com.learn.concurrent;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

/**
 * @desc
 * 四个线程t1, t2, t3, t4, 向4个文件中写入数据，t1只能写入1，t2只能写入2，t3只能写入3，t4只能写入4，对4个文件A，B，C，D写入如下内容:
 * A:123412341234.....
 * B:234123412341....
 * C:341234123412....
 * D:412341234123....
 * 怎么实现同步可以让线程并行工作？
 * @author ji_fei
 * @date  2019-07-16 13:59
 */
public class PrintFileThread2 {
    private static Object aLock = new Object();
    private static Object bLock = new Object();
    private static Object cLock = new Object();
    private static Object dLock = new Object();

    public static boolean aFlag = false;
    public static boolean bFlag = false;
    public static boolean cFlag = false;
    public static boolean dFlag = false;


    public static void write(String path, String character) throws IOException {
        FileWriter aFile = new FileWriter(path, true);
        aFile.write(character);
        aFile.flush();
        aFile.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String aPath = "/Users/ji_fei/Desktop/thread/a.txt";
        String bPath = "/Users/ji_fei/Desktop/thread/b.txt";
        String cPath = "/Users/ji_fei/Desktop/thread/c.txt";
        String dPath = "/Users/ji_fei/Desktop/thread/d.txt";

        Thread aThread = new Thread(() -> {
            while (true) {
                synchronized (dLock) {
                    synchronized (aLock) {
                        try {
                            write(aPath, "1");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        aFlag = true;

                        if (bFlag) {
                            try {
                                write(bPath, "1");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        if (cFlag) {
                            try {
                                write(cPath, "1");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        if (dFlag) {
                            try {
                                write(dPath, "1");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        aLock.notify();

                    }
                    try {
                        dLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread bThread = new Thread(() -> {
            while (true) {
                synchronized (aLock) {
                    synchronized (bLock) {

                        if (aFlag) {
                            try {
                                write(aPath, "2");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        try {
                            write(bPath, "2");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        bFlag = true;
                        if (cFlag) {
                            try {
                                write(cPath, "2");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (dFlag) {
                            try {
                                write(dPath, "2");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        bLock.notify();
                    }
                    try {
                        aLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread cThread = new Thread(() -> {
            while (true) {
                synchronized (bLock) {
                    synchronized (cLock) {
                        if (aFlag) {
                            try {
                                write(aPath, "3");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (bFlag) {
                            try {
                                write(bPath, "3");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        try {
                            write(cPath, "3");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        cFlag = true;
                        if (dFlag) {
                            try {
                                write(dPath, "3");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        cLock.notify();
                    }
                    try {
                        bLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread dThread = new Thread(() -> {
            while (true) {
                synchronized (cLock) {
                    synchronized (dLock) {
                        if (aFlag) {
                            try {
                                write(aPath, "4");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (bFlag) {
                            try {
                                write(bPath, "4");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (cFlag) {
                            try {
                                write(cPath, "4");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        try {
                            write(dPath, "4");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        dFlag = true;

                        dLock.notify();
                    }
                    try {
                        cLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        aThread.start();

        bThread.start();

        cThread.start();

        dThread.start();


    }

}
