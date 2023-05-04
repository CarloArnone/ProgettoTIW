import random
import mysql.connector
import time


def getAuction():
    command = "SELECT * FROM aste"
    cr.execute(command)
    res = cr.fetchall()
    return res[random.randint(0, len(res) -1)]

def getAuctionById(auctionID):
    command = "SELECT * FROM aste WHERE id = " + auctionID
    cr.execute(command)
    res = cr.fetchall()
    return res[0]

def getUser():
    command = "SELECT * FROM utenti"
    cr.execute(command)
    res = cr.fetchall()
    return res[random.randint(0, len(res) -1)]

def getArticle():
    command = "SELECT * FROM articoli"
    cr.execute(command)
    res = cr.fetchall()
    return res[random.randint(0, len(res) -1)]


def getLastOffer(auctionID):
    command = "SELECT * FROM offerte WHERE idAsta = '" + str(auctionID) + "' ORDER BY prezzoOfferto DESC"
    cr.execute(command)
    res = cr.fetchall()
    print(res)
    return res


def generateOffer():
    auction = getAuction()
    auctionCreator = auction[1]
    lastOffer = getLastOffer(auction[0])
    minimumOffer = auction[3]
    if(len(lastOffer) > 0):
        minimumOffer = lastOffer[0][2] + auction[3]
    user = getUser()
    while(user == auctionCreator):
        user = getUser()

    command = "INSERT INTO offerte VALUES('" + user[0] + "', '" + auction[0] + "', " + str(random.randint(minimumOffer, minimumOffer + 100)) + ", CURRENT_TIMESTAMP)"
    time.sleep(2)
    cr.execute(command)
    mydb.commit()



mydb = mysql.connector.connect(
  host="localhost",
  user="carlo",
  password="chiccocarlo001",
  database="dbprogetto"
)

cr = mydb.cursor()
"""
Generate 300 Offers from users/auctions from the db
The images are rightnow only a string

for i in range(0, 300):
    generateOffer()

"""
