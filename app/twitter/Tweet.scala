package twitter

import play.api.libs.json._
import play.api.libs.functional.syntax._
import scala.collection.mutable.ListBuffer
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
	hashtags: Option[Seq[String]]
	//hashTags: Option[List[Map[String, String]]]	// "hashtags" : [ { "text" : "machinelearning", "indices" : [ 107, 123 ] }, { "text" : "javascript", "indices" : [ 124, 135 ] } ] }
)

object Tweet {
	implicit val tweetReads: Reads[Tweet] = (
		(JsPath \ "id_str").read[String] and
		(JsPath \ "created_at").read[String] and
		(JsPath \ "timestamp_ms").read[String] and
		(JsPath \ "timestamp_ms").read[String].map(_.dropRight(3)) and
		(JsPath \ "text").read[String] and
		(JsPath \ "user" \ "name").read[String] and
		(JsPath \ "user" \ "screen_name").read[String] and
		(JsPath \ "user" \ "profile_image_url_https").read[String] and
		(JsPath \ "entities" \ "hashtags" \\ "text").readNullable[ListBuffer[String]].map(s => s)
	)(Tweet.apply _ )

	implicit val tweetWrites: Writes[Tweet] = (
		(JsPath \ "id").write[String] and
		(JsPath \ "timeCreated").write[String] and
		(JsPath \ "timeCreatedMs").write[String] and
		(JsPath \ "timeCreatedUnix").write[String] and
		(JsPath \ "text").write[String] and
		(JsPath \ "userName").write[String] and
		(JsPath \ "screenName").write[String] and
		(JsPath \ "imageUrl").write[String] and
		(JsPath \ "hashtags").writeNullable[Seq[String]]
	)(unlift(Tweet.unapply))
}