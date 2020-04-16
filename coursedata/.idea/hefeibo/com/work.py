
import requests
import json
from selenium import webdriver
import time
from  selenium.webdriver.support.ui import  WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
url ="https://mooc1-1.chaoxing.com/mycourse/studentstudy?chapterId=238340864&courseId=207892287&clazzid=15870146&enc=2ae94a954268f7fb9a0b3ce30a90fffa"
body = {"unameId": "18279667851", "passwordId": "hfs863545025", "numcode": "0431"}
headers = {'content-type': "application/json", 'Authorization': 'APP appid = 4abf1a,token = 9480295ab2e2eddb8'}

#print type(body)
#print type(json.dumps(body))

# 这里有个细节，如果body需要json形式的话，需要做处理
# 可以是data = json.dumps(body)
response = requests.post(url, data = json.dumps(body), headers = headers)

# 也可以直接将data字段换成json字段，2.4.3版本之后支持
# response  = requests.post(url, json = body, headers = headers)

# 返回信息
#print response.text

# 返回响应头
