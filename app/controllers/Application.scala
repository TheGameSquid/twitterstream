package controllers

import play.api._
import play.api.libs.EventSource
import play.api.mvc._
import twitter.TweetStream

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def feed = Action {
    Ok.feed(
      // tweetStreamOut -> Enumerator (Producer)
      // tweetToJson -> Enumeratee (Transformer) to transform Tweets to Json
      // EventSource() -> Enumeratee (Transformer) to transform JsonTweets to Events
      TweetStream.tweetStreamOut through TweetStream.tweetToJson through EventSource()
    ).as("text/event-stream")
  }
}
