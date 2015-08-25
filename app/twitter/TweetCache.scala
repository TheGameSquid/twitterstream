package twitter

import play.api.libs.json._

object TweetCache {
	// FIXME: Obviously remove this
	val sampleJson = Json.obj(
		"stats" -> JsArray(Seq(
			JsObject(Seq(
				"hashtag" -> JsString("Angular"),
				"count" -> JsNumber(256)
			)),
			JsObject(Seq(
				"hashtag" -> JsString("scala"),
				"count" -> JsNumber(912)
			)),
			JsObject(Seq(
				"hashtag" -> JsString("php"),
				"count" -> JsNumber(0)
			))
		))
	)
}
