# MongoDB

MongoDB is a NoSQL database that uses a document-oriented data model, making it highly flexible and scalable. It is designed to handle large volumes of unstructured or semi-structured data, allowing for easy integration with modern applications. MongoDB stores data in JSON-like documents, which can have varying structures, enabling developers to work with complex data types without the need for rigid schemas. Its powerful query language and indexing capabilities make it suitable for a wide range of applications, from content management systems to real-time analytics.

## Maintainers

- Carlos Andreo López <c.andreo@uma.es>

## Short Description

Deploy a virtual machine with MongoDB.

## Long Description

MongoDB is an open-source NoSQL database that uses a document-oriented data model. It is designed for scalability, flexibility and high performance. MongoDB stores data in JSON-like documents with dynamic schemas, allowing for easy integration with modern applications.

## Hypervisors

- one

## Depends on

- tn_bastion

## Tags

- MongoDB

## Site-specific variables

| Variable | Description |
|----------|-------------|
| `template_id` | ID of the MongoDB VM template to use in your OpenNebula environment |
| `image_id` | ID of the MongoDB VM image to use in your OpenNebula environment |

## Input variables

| Variable | Description | Type | Default | Choices | Required When |
|----------|-------------|------|---------|---------|----------------|
| one_mongodb_version | MongoDB version to be installed. Format: X.X. Example: 8.0 | string | 8.0 | - | false |
| one_mongodb_user | Username used to login into the MongoDB | string | admin | - | false |
| one_mongodb_password | Password used to login into the MongoDB. Required to be at least 8 characters long | string | adminadmin | - | false |
| one_mongodb_database | Database name | string | dummydb | - | false |
| one_mongo_express_user | Username used to login into the Mongo Express | string | admin | - | false |
| one_mongo_express_password | Password used to login into the Mongo Express. Required to be at least 8 characters long | string | adminadmin | - | false |

## Generated terraform outputs

Unknown before the deployment, they are stored in the S3 Object Storage, and available to use by future components.  
Each variable has a short description of what it does for informational purposes.

- `__entity_name__component_type`: "mongodb"
- `__entity_name__id`: "VM ID in OpenNebula. Generated from Terraform Manifest"
- `__entity_name__ips`: "Dictionary of VM IP addresses: {<VNet ID in OpenNebula>: <IP address>}. Generated from Terraform Manifest"
