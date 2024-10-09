import time
import requests
from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
#from selenium.webdriver.chrome.service import Service
import pandas as pd



url = 'https://www.youtube.com/c/JohnWatsonRooney/videos'

#cService = webdriver.ChromeService(executable_path='/usr/bin/chromedriver')
#driver = webdriver.Chrome(service = cService)



#chrome_service = Service(executable_path='/usr/bin/chromedriver')
#driver = webdriver.Chrome(service=chrome_service)


driver = webdriver.Chrome('/usr/bin/chromedriver')
driver.get(url)
time.sleep(3)
SCROLL_PAUSE_TIME = 1

# Get scroll height
"""last_height = driver.execute_script("return document.body.scrollHeight")

this dowsnt work due to floating web elements on youtube
"""

last_height = driver.execute_script("return document.documentElement.scrollHeight")
while True:
    # Scroll down to bottom
    driver.execute_script("window.scrollTo(0,document.documentElement.scrollHeight);")

    # Wait to load page
    time.sleep(SCROLL_PAUSE_TIME)

    # Calculate new scroll height and compare with last scroll height
    new_height = driver.execute_script("return document.documentElement.scrollHeight")
    
    if new_height == last_height:
       print("break")
       break
    last_height = new_height
    videos = driver.find_elements_by_class_name('style-scope ytd-grid-video-renderer')


video_list=[]

for video in videos:
    
    title = video.find_elements_by_xpath('.//*[@id="video-title"]')[0].text
    views = video.find_elements_by_xpath('.//*[@id="metadata-line"]/span[1]')[0].text
    when = video.find_elements_by_xpath('.//*[@id="metadata-line"]/span[2]')[0].text
    links=video.find_elements_by_xpath('//*[@id="video-title"]')
    for link in links:
        url=link.get_attribute("href")
    
    print(title,views,when,url )
    vid_item ={
        'Title' : title,
        'Views' : views,
        'posted': when,
        'URL'   : url
    }
    video_list.append(vid_item)
df = pd.DataFrame(video_list)
print(df)
df.to_csv('John watson ronney.csv')
print('Saved to csv file')