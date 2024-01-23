package main.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleEntity extends BaseEntity {
//    id(uuid),title,description,content,shared_count,image_id,
//    region_id,category_id,moderator_id,publisher_id,status(Published,NotPublished)
//    created_date,published_date,visible,view_count
//            (Bitta article bir-nechta type da bo'lishi mumkun. Masalan Asosiy,Muharrir da.)
    private String title;
    private String description;
    private String content;
    private String shared_count;
    private String image_id;

}
