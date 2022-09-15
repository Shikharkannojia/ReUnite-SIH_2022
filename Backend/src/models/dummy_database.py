import mongoengine

class DummyDetails(mongoengine.Document):
    userType = mongoengine.StringField()
    imageURL = mongoengine.StringField()

    meta = {
        'db_alias': 'core',
        'collection': 'DummyDetails'
    }

class DummyImages(mongoengine.Document):
    userType = mongoengine.StringField()
    faceEncoding = mongoengine.BinaryField()
    isFound = mongoengine.BooleanField(default=False)

    meta = {
        'db_alias': 'core',
        'collection': 'DummyImages'
    }
