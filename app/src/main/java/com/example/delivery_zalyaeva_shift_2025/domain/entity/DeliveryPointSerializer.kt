package com.example.delivery_zalyaeva_shift_2025.domain.entity

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder.Companion.DECODE_DONE
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure


class DeliveryPointSerializer : KSerializer<DeliveryPoint> {
    override val descriptor: SerialDescriptor
        get() = buildClassSerialDescriptor("DeliveryPoint") {
            element<String>("id")
            element<String>("name")
            element<Float>("latitude")
            element<Float>("longitude")
        }

    override fun serialize(encoder: Encoder, value: DeliveryPoint) {
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value.id)
            encodeStringElement(descriptor, 1, value.name)
            encodeFloatElement(descriptor, 2, value.latitude)
            encodeFloatElement(descriptor, 2, value.longitude)
        }
    }

    override fun deserialize(decoder: Decoder): DeliveryPoint {
        return decoder.decodeStructure(descriptor) {
            var id: String? = null
            var name: String? = null
            var latitude: Float? = null
            var longitude: Float? = null

            loop@ while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    DECODE_DONE -> break@loop

                    0 -> id = decodeStringElement(descriptor, 0)
                    1 -> name = decodeStringElement(descriptor, 1)
                    2 -> latitude = decodeFloatElement(descriptor, 2)
                    3 -> longitude = decodeFloatElement(descriptor, 3)

                    else -> throw SerializationException("Unexpected index $index")
                }
            }

            DeliveryPoint(
                requireNotNull(id),
                requireNotNull(name),
                requireNotNull(latitude),
                requireNotNull(longitude)
            )
        }
    }
}