import sqlite3
# Connexion à la base de données SQLite
conn = sqlite3.connect('employes.db')

# Création d'un curseur
cursor = conn.cursor()

# Création de la table employee
cursor.execute('''
    CREATE TABLE IF NOT EXISTS employee (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        name TEXT NOT NULL,
        salary REAL NOT NULL
    )
''')
# Insertion de données dans la table
cursor.execute("INSERT INTO employee (name, salary) VALUES ('Moussa', 60000)")
cursor.execute("INSERT INTO employee (name, salary) VALUES ('Baba', 40000)")
cursor.execute("INSERT INTO employee (name, salary) VALUES ('Charl', 70000)")
cursor.execute("INSERT INTO employee (name, salary) VALUES ('David', 30000)")
cursor.execute("INSERT INTO employee (name, salary) VALUES ('Emma', 55000)")

# Valider les changements
conn.commit()
# Étape 4: Récupérer et afficher les employés avec un salaire supérieur à 50 000
cursor.execute("SELECT name, salary FROM employee WHERE salary > 50000")
employees = cursor.fetchall()

# Affichage des résultats
for employee in employees:
    print(f"Name: {employee[0]}, Salary: {employee[1]}")
# Étape 5: Fermer la connexion
conn.close()
