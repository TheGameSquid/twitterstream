package controllers

import play.api._
import play.api.libs.EventSource
import play.api.mvc._
import twitter.TweetStream
import twitter.TweetCache

class Application extends Controller {
  def feed = Action {
    Ok.feed(
      // tweetStreamOut -> Enumerator (Producer)
      // tweetToJson -> Enumeratee (Transformer) to transform Tweets to Json
      // EventSource() -> Enumeratee (Transformer) to transform JsonTweets to Events
      TweetStream.tweetStreamOut through TweetStream.tweetToJson through EventSource()
    ).as("text/event-stream")
  }

  def sampleJson = Action {
    Ok(TweetCache.completeJson)
  }
}