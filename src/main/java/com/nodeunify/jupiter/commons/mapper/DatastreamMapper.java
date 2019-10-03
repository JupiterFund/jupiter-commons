package com.nodeunify.jupiter.commons.mapper;

import com.gta.qts.c2j.adaptee.structure.SSEL2_Quotation;
import com.gta.qts.c2j.adaptee.structure.SZSEL2_Quotation;
import com.nodeunify.jupiter.datastream.v1.Order;
import com.nodeunify.jupiter.datastream.v1.Quote;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import cn.com.wind.td.tdf.TDF_MARKET_DATA;
import cn.com.wind.td.tdf.TDF_ORDER;

@Mapper
public interface DatastreamMapper {

    DatastreamMapper MAPPER = Mappers.getMapper(DatastreamMapper.class);

    // Mappings for Quote
    @Mapping(source = "TotalNo", target = "numTrades")
    Quote map(SSEL2_Quotation data);

    @Mapping(source = "TotalNo", target = "numTrades")
    Quote map(SZSEL2_Quotation data);

    @Mapping(source = "code", target = "code")
    @Mapping(source = "numTrades", target = "numTrades")
    Quote map(TDF_MARKET_DATA data);

    // Mappings for Order
    @Mapping(source = "code", target = "code")
    Order map(TDF_ORDER data);

}
