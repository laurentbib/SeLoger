package com.biblublab.data.repository

import com.biblublab.data.common.*
import com.biblublab.data.remote.RealPropertyDataResponse
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RealPropertyMapperTest {

    private lateinit var realPropertyMapper: RealPropertyMapper

    @Before
    fun setup() {
        realPropertyMapper = RealPropertyMapper()
    }

    @Test
    fun responseObjectIsTheSameDomainObject() {
        //given
        val response = RealPropertyDataResponse(ID, BEDROOMS, CITY, AREA, URL, PRICE, PROFESSIONAL, TYPE, ROOMS)
        //when
        val result = realPropertyMapper.toRealProperty(response)
        //then
        assertEquals(result.id, response.id)
    }
}