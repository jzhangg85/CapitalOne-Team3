import sys
import requests
import json
import urllib3
from urllib.request import urlopen
from random import randrange
import codecs


customerId = '574702718a710f8e12324ee0'
apiKey = '482cef6d93c206eb7e5c9fefc8941ccb'

url = 'http://api.reimaginebanking.com/enterprise/merchants?key=482cef6d93c206eb7e5c9fefc8941ccb'
response = urlopen(url)

data= requests.get(url,
headers={'content-type':'application/json'},
)



	#response = requests.get('http://api.reimaginebanking.com/enterprise/merchants?key=482cef6d93c206eb7e5c9fefc8941ccb')
	#response.json

	#data = json.load(response)
json_data = json.loads(data.text)

type(json_data)

for result in json_data:
	type(result)
	print(result[0])
