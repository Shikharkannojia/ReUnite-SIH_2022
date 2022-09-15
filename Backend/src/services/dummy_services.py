import pickle
from src.models.dummy_database import DummyDetails
from src.models.dummy_database import DummyImages
from src.face_detection import CompareFace, Face

def insert(dummy_details):
    userType = dummy_details.get('userType', None)
    imageURL = dummy_details.get('imageDetails', None)
    acknowledge = {
        'updated': True
    }

    if imageURL:
        face = Face(path=imageURL)
        faceEncoding = face.return_face_encoding()
    
    try:
        if imageURL:
            dummy_detail = DummyDetails()
            dummy_detail.userType = userType
            dummy_detail.imageURL = imageURL
            dummy_detail.save()

            dummy_image = DummyImages()
            dummy_image.userType = userType
            dummy_image.faceEncoding = faceEncoding
    except:
        acknowledge['updated'] = False
    
    return acknowledge

def compare_dummy_face(info):
    userType = info.get('userType', None)
    imageURL = info.get('imageDetails', None)
    child_info = {
        'found': False,
        'confidence': 0,
        'message': 'Not Present in Database'
    }
    face_encodings = []
    try:
        dummy_images = DummyImages.objects(isFound=False).as_pymongo()
        length_faces = len(dummy_images)
        face_encodings = [dummy_images[index]['faceEncoding'] for index in range(length_faces)]
        face_encodings = list(map(pickle.loads, face_encodings))
    except:
        child_info['message'] = 'Database is Empty'

    if imageURL:
        cface = CompareFace()
        child_update = cface.compare_face(imageURL, face_encodings)
        if child_update['index'] > -1:
            child_info['message'] = 'Found'
        else:
            child_info['message'] = 'Not Found'
    
    else:
        child_info['message'] = 'Not Found'
    
    return child_info






