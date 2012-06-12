import cgi
from django.utils import simplejson as json
from google.appengine.api import users
from google.appengine.ext import webapp
from google.appengine.ext.webapp.util import run_wsgi_app
from google.appengine.ext import db

class Score(db.Model):
	nickname = db.StringProperty()
	score = db.IntegerProperty()
	level = db.IntegerProperty()
	date = db.DateTimeProperty(auto_now_add=True)
	
class MainPage(webapp.RequestHandler):
	def get(self):
		self.response.headers['Content-Type'] = 'text/plain'
		self.response.out.write('Game Dev Conference')
	
class UploadScore(webapp.RequestHandler):
	def get(self):
		self.response.headers['Content-Type'] = 'text/plain'
		self.response.out.write('Upload Score Page')
		
	def post(self):
		# Player or Nickname
		# Current score
		# Level played
		scoreInstance = Score()
		player = self.request.get('player')
		myscore = self.request.get('score')
		level = self.request.get('level')
		
		try:
			myscore = int(myscore)
			level = int(level)
		except ValueError:
			print 'Error'
		
		scoreInstance.nickname = player
		scoreInstance.score = myscore
		scoreInstance.level = level
		scoreInstance.put()
		
class GetScore(webapp.RequestHandler):
	def get(self):
		# Level played
		level = self.request.get('level')
		query = "SELECT * FROM Score WHERE level = %s ORDER BY score DESC LIMIT 20" % level
		scores = db.GqlQuery(query)
		
		json_output = []
		
		for theScore in scores:
			levelSTR = "Easy"
			if theScore.level == 1:
				levelSTR = "Normal"
			if theScore.level == 2:
				levelSTR = "Hard"
			json_list = {"nickname":theScore.nickname, "score":theScore.score, "level":levelSTR}
			json_output.append(json_list)
		
		encoded_str = json.dumps(json_output)
		self.response.headers['Content-Type'] = 'application/json'
		self.response.out.write(encoded_str)
		
	def post(self):
		# Level played
		level = self.request.get('level')
		query = "SELECT * FROM Score WHERE level = %s ORDER BY score DESC LIMIT 20" % level
		scores = db.GqlQuery(query)
		
		json_output = []
		
		for theScore in scores:
			levelSTR = "Easy"
			if theScore.level == 1:
				levelSTR = "Normal"
			if theScore.level == 2:
				levelSTR = "Hard"
			json_list = {"nickname":theScore.nickname, "score":theScore.score, "level":levelSTR}
			json_output.append(json_list)
		
		encoded_str = json.dumps(json_output)
		self.response.headers['Content-Type'] = 'application/json'
		self.response.out.write(encoded_str)
		
application = webapp.WSGIApplication([('/', MainPage),('/uploadScore', UploadScore), ('/getScore', GetScore)],debug=True)

def main():
	run_wsgi_app(application)
	
if __name__ == "__main__":
	main()