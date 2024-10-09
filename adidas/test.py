from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.chrome.service import Service

# python -m venv venv 
# source ./venv/bin/activate (for linux) 
# run actvate.bat in venv bin folder 
## download chromedriver.zip and upzip in a folder withexecute and read permissions  
## path=r"/usr/bin/chromedriver"
# pip install selenium
# pyhon test.py


CHROMEDRIVER_PATH = '/usr/bin/google-chrome'
#CHROMEDRIVER_PATH = '/home/sathish/chromedriver/chromedriver'


service = Service(executable_path=r'/usr/bin/google-chrome')
options = webdriver.ChromeOptions()
options.add_argument('--headless')
options.add_argument('--no-sandbox')
options.add_argument('--disable-dev-shm-usage')
driver = webdriver.Chrome(service=service, options=options)
driver.get("https://www.browserstack.com/")
element = driver.find_element(By.NAME, "query")
assert element.is_enabled()

driver.quit()