# spring-data-jpa-search-criteria
This is library that includes feature of filtering sorting and pagination. <br>
Use DefaultSearchService.kt for it. Give to DefaultSearchService.search function a Request.class that u will get from body of http request, and class of entity that u want filter, sort and paging.<br>
U will get SearchResponse<ModelClass> that have list of filtered and sorted items, page number, page size and count of model that was found in DB by this query. 
