package de.leanovate.swaggercheck.schema

import com.fasterxml.jackson.databind.node.JsonNodeFactory
import de.leanovate.swaggercheck.SwaggerChecks
import org.scalatest.mock.MockitoSugar
import org.scalatest.{MustMatchers, WordSpec}

class ArrayDefinitionSpec extends WordSpec with MustMatchers with MockitoSugar {
  "ArrayDefinition" should {
    "fail verify for non array nodes" in {
      val mockContext = mock[SwaggerChecks]
      val arrayDefinition = ArrayDefinition(None, None, None)

      arrayDefinition.verify(mockContext, Seq.empty, JsonNodeFactory.instance.booleanNode(false)).isSuccess mustBe false
      arrayDefinition.verify(mockContext, Seq.empty, JsonNodeFactory.instance.objectNode()).isSuccess mustBe false
      arrayDefinition.verify(mockContext, Seq.empty, JsonNodeFactory.instance.numberNode(0)).isSuccess mustBe false
      arrayDefinition.verify(mockContext, Seq.empty, JsonNodeFactory.instance.textNode("")).isSuccess mustBe false
    }
  }

}