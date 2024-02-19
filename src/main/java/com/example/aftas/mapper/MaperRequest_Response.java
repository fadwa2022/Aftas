package com.example.aftas.mapper;


public interface  MaperRequest_Response<DtoRequest, DtoResponse> {


    DtoResponse mapRequestToResponse(DtoRequest dtoRequest);
    DtoRequest mapResponseToRequest(DtoResponse dtoResponse);

}