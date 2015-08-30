package twitter

import play.api.libs.json.JsValue
import org.json4s._
import org.json4s.native.JsonMethods
import org.json4s.native.Serialization
import scala.util.{Try, Failure, Success}

/** Container for the tweet message received from the stream **/
// FIXME: Add potential Geolocation
case class Tweet(
	id: String,						// "id_str"
	timeCreated: String,			// "created_at"
	timeCreatedMs: String,			// "timestamp_ms"
	timeCreatedUnix: String,		// "timestamp_ms"
	text: String,					// "text"
	userName: String,				// "user": { ... , ... , "name": "Joe", ... }
	screenName: String,				// "user": { ... , ... , "screen_name": "therealjoe", ... }
	profileImageUrl: String,		// "user": { ... , ... , "profile_image_url_https": **** , ... }
	hashtags: List[String]	// "hashtags" : [ { "text" : "machinelearning", "indices" : [ 107, 123 ] }, { "text" : "javascript", "indices" : [ 124, 135 ] } ] }
)
{
	def toJson: JsValue = {
		implicit val formats = DefaultFormats
		import play.api.libs.json._

		Json.parse(Serialization.write(this))
	}
}

object Tweet {
	def fromJson(jsonTweet: JValue): Try[Tweet] = {
		implicit val formats = DefaultFormats

		try {
			val id 				= (jsonTweet \ "id").extract[String]
			val timeCreated 	= (jsonTweet \ "created_at").extract[String]
			val timeCreatedMs 	= (jsonTweet \ "timestamp_ms").extract[String]
			val timeCreatedUnix = (jsonTweet \ "timestamp_ms").extract[String].dropRight(3)
			val text 			= (jsonTweet \ "text").extract[String]
			val userName 		= (jsonTweet \ "user" \ "name").extract[String]
			val screenName 		= (jsonTweet \ "user" \ "screen_name").extract[String]
			val imageUrl 		= (jsonTweet \ "user" \ "profile_image_url_https").extract[String]
			val hashtags		= (jsonTweet \ "entities" \ "hashtags" \ "text").values match {
				case hashtag: String 		=> List(hashtag)
				case hashtags: List[String] => hashtags.distinct
				//case None 					=> List.empty
				case _						=> List.empty
			}
			Success(Tweet(id, timeCreated, timeCreatedMs, timeCreatedUnix, text, userName, screenName, imageUrl, hashtags))
		}
		catch {
			case _ => Failure(new Exception("Failed to parse the JSON Tweet object"))
		}
	}
}