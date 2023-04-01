package com.example.treestructure.presentation.utils

import org.komputing.khash.keccak.Keccak
import org.komputing.khash.keccak.KeccakParameter
import javax.inject.Inject

class HashNameGenerator @Inject constructor() {

    private var lastDigitsAmount = 20

    /**
     * @return String with the length [lastDigitsAmount].
     */
    fun generate(hashCode: Int): String {
        val byteList = mutableListOf<Byte>()
        hashCode.toString().forEach { hashCodeChar ->
            byteList.add(hashCodeChar.code.toByte())
        }
        return Keccak.digest(byteList.toByteArray(), KeccakParameter.KECCAK_256).toHex()
            .takeLast(lastDigitsAmount)
    }
}

private fun ByteArray.toHex(): String =
    joinToString(separator = "") { eachByte ->
        "%02x".format(eachByte)
    }
