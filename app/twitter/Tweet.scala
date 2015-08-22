package twitter

import play.api.libs.json.JsValue

/** Container for the JSON tweet message received from the stream **/
// FIXME: Provide actual deserialized model for the message
case class Tweet(json: JsValue)
