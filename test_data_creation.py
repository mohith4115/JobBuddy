from faker import Faker
from pymongo import MongoClient
import random
from datetime import datetime
from bson.objectid import ObjectId

fake = Faker()

# MongoDB Connection
client = MongoClient("mongodb://localhost:27017/")
db = client["JobBuddy"]  # Replace with your database name

# Collections
users_collection = db["users"]
jobs_collection = db["jobs"]
applications_collection = db["applications"]

# Clear existing data (optional)
users_collection.delete_many({})
jobs_collection.delete_many({})
applications_collection.delete_many({})

# Dummy Data Generation

# Users
users = []
for _ in range(2000):
    user = {
        "username": fake.user_name(),
        "email": fake.email(),
        "password_hash": fake.sha256(),
        "role": random.choice(["user", "recruiter"]),
        "profile": {
            "skills": random.sample(["Java", "Python", "Spring Boot", "React.js", "MongoDB", "Docker", "Kubernetes", "Elasticsearch", "Kafka", "spaCy", "BERT", "AI", "ML"], random.randint(2, 5)),
            "experience": f"{random.randint(0, 10)} years",
            "resume": fake.url()
        }
    }
    users.append(users_collection.insert_one(user).inserted_id)

# Jobs
jobs = []
for _ in range(1000):
    job = {
        "title": fake.job(),
        "company": fake.company(),
        "location": fake.city(),
        "skills_required": random.sample(["Java", "Python", "Spring Boot", "React.js", "MongoDB", "Docker", "Kubernetes", "Elasticsearch", "Kafka", "spaCy", "BERT", "AI", "ML"], random.randint(3, 7)),
        "description": fake.text(max_nb_chars=500)
    }
    jobs.append(jobs_collection.insert_one(job).inserted_id)

# Applications
for _ in range(1500):
    application = {
        "user_id": random.choice(users),
        "job_id": random.choice(jobs),
        "status": random.choice(["Applied", "Shortlisted", "Rejected"])
    }
    applications_collection.insert_one(application)

client.close()
print("Dummy data generated successfully!")