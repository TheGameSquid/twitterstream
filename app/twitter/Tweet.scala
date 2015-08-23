package twitter

import play.api.libs.json._
import play.api.libs.functional.syntax._
import scala.util.{Try, Failure, Success}

/** Container for the tweet message received from the stream **/
// FIXME: Add potential Geolocation
case class Tweet(
	id: String,						// "id_str"
	createdAt: String,				// "created_at"
	createdAtMs: String,			// "timestamp_ms"
	text: String,					// "text"
	userName: String,				// "user": { ... , ... , "name": "Joe", ... }
	screenName: String				// "user": { ... , ... , "screen_name": "therealjoe", ... }
)
{
	implicit val tweetWrites = new Writes[Tweet] {
		def writes(tweet: Tweet) = Json.obj(
			"id" 			-> tweet.id,
			"created_at" 	-> tweet.createdAt,
			"created_at_ms" -> tweet.createdAtMs,
			"text" 			-> tweet.text,
			"userName" 		-> tweet.userName,
			"screenName" 	-> tweet.screenName
		)
	}

	def toJson: JsValue = {
		Json.toJson(this)
	}
}

object Tweet {
	implicit val tweetReads: Reads[Tweet] = (
		(JsPath \ "id").read[String] and
		(JsPath \ "created_at").read[String] and
		(JsPath \ "timestamp_ms").read[String] and
		(JsPath \ "text").read[String] and
		(JsPath \ "user" \ "name").read[String] and
		(JsPath \ "user" \ "screen_name").read[String]
	)(Tweet.apply _ )

	def fromJson(json: JsValue): Try[Tweet] = {
		json.validate[Tweet] match {
			case s: JsSuccess[Tweet] => Success(s.get)
			case e: JsError => Failure(new Exception("Unable to validate JsValue as JSON Tweet"))
		}
	}
}