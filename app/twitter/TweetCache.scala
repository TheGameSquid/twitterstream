package twitter

import play.api.libs.json._

object TweetCache {
	// FIXME: Obviously remove this
	val sampleJson = Json.toJson(
		Seq(
			Map(
				"label" -> Json.toJson("php"),
				"value" -> Json.toJson(0)
			),
			Map(
				"label" -> Json.toJson("scala"),
				"value" -> Json.toJson(2512)
			),
			Map(
				"label" -> Json.toJson("java"),
				"value" -> Json.toJson(132)
			),
			Map(
				"label" -> Json.toJson("ruby"),
				"value" -> Json.toJson(5423)
			),
			Map(
				"label" -> Json.toJson("python"),
				"value" -> Json.toJson(3431)
			),
			Map(
				"label" -> Json.toJson("perl"),
				"value" -> Json.toJson(1)
			),
			Map(
				"label" -> Json.toJson("node.js"),
				"value" -> Json.toJson(4311)
			)
		)
	)

	val completeJson = Json.toJson(
		Seq(
			Map(
				"key" -> Json.toJson("Count of tweets grouped by subject"),
				"values" -> sampleJson
			)
		)
	)
}
