package cl.rogarciap.marketapp.services.mappers

import cl.rogarciap.marketapp.dtos.ProductDTO
import cl.rogarciap.marketapp.models.ProductModel

fun List<ProductDTO>.FromListDtoToListModel(): List<ProductModel> = map {
    ProductModel(
        uuid = it.uuid,
        description = it.description,
        code = it.code,
        features = it.features,
        price = it.price,
        stock = it.stock,
        images = it.images
    )
}