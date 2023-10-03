---
title: "Mongodb"
date: 2023-10-03T23:10:05+02:00
draft: false
description: "A few note working with MongoDB and tools"
---


## Create compound index 
Code:
```python
class ReportedUser(Document):
    userid: str
    tags: list[str]
    note: str
    platformUrl: str
    relatedPlatforms: list[str]

    class Settings:
        name = "reported_user"
        indexes = [
            IndexModel(
                [("userid", TEXT), ("platformUrl", TEXT)],
                unique=True, name="uid_platformurl_unique",
            )
        ]
```

Resources:
- https://beanie-odm.dev/tutorial/indexes/
- https://www.mongodb.com/docs/manual/core/indexes/index-types/index-compound/
- https://stackoverflow.com/questions/30480781/multiple-field-indexing-in-pymongo
- https://pymongo.readthedocs.io/en/stable/api/pymongo/operations.html#pymongo.operations.IndexModel