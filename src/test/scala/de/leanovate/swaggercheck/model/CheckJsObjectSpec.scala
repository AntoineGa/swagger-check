package de.leanovate.swaggercheck.model

import org.scalacheck.Shrink
import org.scalatest.{MustMatchers, WordSpec}

class CheckJsObjectSpec extends WordSpec with MustMatchers {
  "JsObject" should {
    "shrink without order or required" in {
      val original = CheckJsObject(Set.empty, None, Map(
        "one" -> CheckJsInteger(None, None, 1000000),
        "two" -> CheckJsUnformattedString(None, "0123456789abcdefghijklmnopqrstuvwxyz"),
        "three" -> CheckJsBoolean(true),
        "four" -> CheckJsBoolean(false),
        "five" ->CheckJsInteger(None, None, 10000),
        "six" -> CheckJsUnformattedString(None, "zyxwvutsrqponmlkjihgfedcba9876543210")
      ))
      val originalJson = original.minified

      val shrink = Shrink.shrink[CheckJsObject](original)

      shrink must not be empty
      shrink.foreach {
        value =>
          value.minified.length must be < originalJson.length
      }
    }
  }
}
