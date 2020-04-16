import sys
import io
from urllib import request
from selenium import webdriver
import time
from  selenium.webdriver.support.ui import  WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
sys.stdout = io.TextIOWrapper(sys.stdout.buffer,encoding='utf8') #改变标准输出的默认编码
#登录后才能访问的网站
url = 'https://mooc1-2.chaoxing.com/mycourse/studentstudy?chapterId=201003583&courseId=206568261&clazzid=15848070&enc=18d60a0f9cb96554472cd102746f505b'
#url = 'https://mooc1-2.chaoxing.com/mycourse/studentstudy?chapterId=201003577&courseId=206568261&clazzid=15848070&enc=18d60a0f9cb96554472cd102746f505b'
#浏览器登录后得到的cookie，也就是刚才复制的字符串
cookie_str = 'k8s=e148e81639260772bcaf08311e0dc7a6891b6db8; jrose=7E9CF6176C0E6107D004027E4567D3DA.mooc-836929377-2sf12; route=3cfd8ee391150acbf63626fecc6e7627; uname=183051324; fid=1361; pid=34736; _uid=79984690; uf=b2d2c93beefa90dc884f55c68d3951b91c98acb0cbe62eb4c42208843ed2e28879fd8c9b75cb45939d0a96fdd05c38dec49d67c0c30ca5047c5a963e85f1109956fe3d66d3759fc4ce71fc6e59483dd32e4668eebffb8e24cfff635ce3c297a77ac3904e6616eafe; _d=1583117950248; UID=79984690; vc=29425334511DDB6CFDC25E3434AD61BD; vc2=C61DEF8D0BDBC9FB6A82D8BF3E0D93EB; vc3=eDGhCkgiGdrfm2WC4mBFdN4PRRlHMexUap0el%2Bedx%2FjyELfrpZ5ShGTNSHXbpupLYF8JQYTfSHJUcNL4xBBX69OJ%2FuYQ5hInWF8r%2BJB4vKO7H3v2DW8g2tWDWY4EOj8o%2BBXnog%2FSaGbGLxxnxdzpVL1ofapN9eM7REVeh70YSNA%3Daf4740682acdc2a906ffc060a8c08829; xxtenc=e245c198117d1ee2fb78b8bb2dd69854; DSSTASH_LOG=C_38-UN_757-US_79984690-T_1583117950249; thirdRegist=0'
#登录后才能访问的网页
url = 'https://mooc1-2.chaoxing.com/mycourse/studentstudy?chapterId=201003577&courseId=206568261&clazzid=15848070&enc=18d60a0f9cb96554472cd102746f505b'
req = request.Request(url)
#设置cookie
req.add_header('cookie', cookie_str)
#设置请求头
req.add_header('User-Agent', 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36')
#resp = request.urlopen(req)
drive=webdriver.Chrome()
drive.get("https://mooc1-2.chaoxing.com/mycourse/studentcourse?courseId=206568261&clazzid=15848070&vc=1&cpi=65439536&enc=18d60a0f9cb96554472cd102746f505b")
drive.find_element_by_id("unameId").send_keys("18279667851")
drive.find_element_by_id("passwordId").send_keys("hfs863545025")
drive.find_element_by_id("numcode").send_keys("8313")
drive.find_element_by_class_name("zl_btn_right").click()
video=WebDriverWait(drive,30,0.5).until(EC.presence_of_element_located((By.XPATH,"//*[@id='bilibiliPlayer']/div[1]/div[2]/div[7]/video")))  # 找到视频
url=drive.execute_script("return arguments[0].currentSrc;",video)
print(resp.read().decode('utf-8'))