package com.hefeibo;

import java.io.File;

public class DeleteSongs {
    public static void main(String[] args) {
        String path="此电脑\\DUB-AL00\\内部存储\\KuwoMusic\\music";
        getFiles(path);
    }
    public static void getFiles(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    System.out.println("目录：" + files[i].getPath());
                    getFiles(files[i].getPath());
                } else {
                    String fileName = files[i].getName();
                    System.out.println("---------");
                    if(fileName.contains("复制")){
                        file.delete();
                    }
                }
                    /**System.out.println("文件：" + files[i].getPath());
                    String fileName = files[i].getName();
                    String newfileName = fileName.substring(0,fileName.lastIndexOf("-"));
                    newfileName = newfileName+".mp3";
                    System.out.println(newfileName);
                    File newDir = new File("D:\\我的音乐\\jay1" + "/" + newfileName);
                    file.renameTo(newDir);
                    //System.out.println("修改后：" + newDir);
                }

            }
        } else {
            System.out.println("文件：" + file.getPath());
        }**/
            }
        }
    }
}