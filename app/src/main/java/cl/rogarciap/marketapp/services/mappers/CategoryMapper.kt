package cl.rogarciap.marketapp.services.mappers

import cl.rogarciap.marketapp.dtos.CategoryDTO
import cl.rogarciap.marketapp.entities.CategoryEntity
import cl.rogarciap.marketapp.models.CategoryModel

fun List<CategoryDTO>.FromListDtoToListModel(): List<CategoryModel> = map {
    CategoryModel(
        uuid = it.uuid,
        name = it.name,
        image = it.image
    )
}

fun List<CategoryModel>.FromListModelToListEntity(): List<CategoryEntity> = map {
    CategoryEntity(
        uuid = it.uuid,
        name = it.name,
        image = it.image,
        status = true
    )
}

fun List<CategoryEntity>.FromListEntityToListModel(): List<CategoryModel> = map {
    CategoryModel(
        uuid = it.uuid,
        name = it.name,
        image = it.image
    )
}

fun CategoryEntity.toModel(): CategoryModel {
    return CategoryModel(
        uuid = uuid,
        name = name,
        image = image
    )
}