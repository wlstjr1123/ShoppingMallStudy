package part5.jjs.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import part5.jjs.domain.model.SearchKeyword

@Entity(tableName = "search")
data class SearchKeywordEntity(
    @PrimaryKey
    val keyword: String
)

fun SearchKeywordEntity.toDomain(): SearchKeyword {
    return SearchKeyword(keyword)
}
