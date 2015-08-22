package twitter

import akka.actor.Actor
import play.api.libs.oauth.OAuthCalculator
import play.api.libs.ws.WS
import play.api.Play.current

class TweetStreamActor extends Actor {
	// We're using the default Play! executioncontext
	import play.api.libs.concurrent.Execution.Implicits.defaultContext
	import TweetStreamActor._

	def receive = {
		case TweetStreamStart => {
			WS.url(TweetStream.twitterUrl + TweetStream.twitterTopics)
				// FIXME: This should be set to -1 once Play bug gets fixed, GitHub issue 4846
				.withRequestTimeout(Long.MaxValue)
				// Sign the Request, because Twitter requires oAuth
				.sign(OAuthCalculator(TweetStream.consumerKey, TweetStream.accessToken))
				// Get the stream, and then consume it with the tweetIteratee
				.get(TweetStream.tweetIteratee)
		}
	}
}

object TweetStreamActor {
	/** Indicates that the TweetStreamActor should start the WS connection to Twitter **/
	case object TweetStreamStart
}
