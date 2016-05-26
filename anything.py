import sys
import requests
import json
import urllib3
from urllib.request import urlopen
from random import randrange
import codecs

def main ():
	merchants = []
	print(type)

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




if __name__ == "__main__":
	main()