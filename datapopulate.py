import sys
import requests
import json
import urllib3
from urllib.request import urlopen
from random import randrange
import codecs


def main():
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
	# print(json_data)

	merchants = []

	for result in json_data:
		if (result["address"]["city"] == "Philadelphia"):
			merchants.append(result)
	print(merchants)

	for i in range(1,1000):
		index = randrange(0, len(merchants))
		merchant = merchants[index]["id"]
		url = 'http://api.reimaginebanking.com/customers/{}/accounts?key={}'.format(customerId,apiKey)
		payload = {
  		"merchant_id": merchant,
  		"medium":  "",
  		"purchase_date": "2016-05-26",
  		"amount": randrange(0,10),
  		"status": "pending",
  		"description": "string"
		}
		response = requests.post(
		url,
		data=json.dumps(payload),
		headers={'content-type':'application/json'},
		)







	#merchants = []



	# url = 'http://api.reimaginebanking.com/customers/{}/accounts?key={}'.format(customerId,apiKey)
	# payload = {
 #  		"merchant_id": "string",
 #  		"medium": "balance",
 #  		"purchase_date": "2016-05-26",
 #  		"amount": 0,
 #  		"status": "pending",
 #  		"description": "string"
	# }

	# response = requests.post(
	# 	url,
	# 	data=json.dumps(payload),
	# 	headers={'content-type':'application/json'},
	# 	)

	# if response.status_code == 201:
	# 	print('account created')


	customerId = '574702718a710f8e12324ee0'
	apiKey = '482cef6d93c206eb7e5c9fefc8941ccb'

	with open('merchants.json') as data_file:
		print(type(data_file))
		print(data_file)
	files = open('jsonformatter.txt')

	json_data = json.load(files)


	merchants = []

	for result in json_data["results"]:
		if "address" in result:
			if "city" in result["address"]:
				if result["address"]["city"] == "Philadelphia":
					merchants.append(result)




	for i in range(1,1000):
		index = randrange(0, len(merchants))
		merchant = merchants[index]["_id"]
		url = 'http://api.reimaginebanking.com/customers/{}/accounts?key={}'.format(customerId,apiKey)
		payload = {
		"merchant_id": merchant,
		"medium":  "",
		"purchase_date": "2016-05-26",
		"amount": randrange(0,10),
		"status": "pending",
		"description": "string"
		}
		response = requests.post(
		url,
		data=json.dumps(payload),
		headers={'content-type':'application/json'},
		)




if __name__ == "__main__":
	main()
