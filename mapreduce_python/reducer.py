#!/usr/bin/env python
import sys
current_word = None
current_count = 0
# Lecture depuis STDIN (sortie du mapper)
for line in sys.stdin:
    line = line.strip()  # supprimer espaces en début/fin
    if not line:
        continue
    # Séparer mot et valeur
    try:
        word, count = line.split('\t', 1)
        count = int(count)
    except ValueError:
        continue  # ignorer les lignes malformées
    # Agrégation des occurrences
    if current_word == word:
        current_count += count
    else:
        if current_word is not None:
            # afficher le résultat pour le mot précédent
            print(f'{current_word}\t{current_count}')
        current_word = word
        current_count = count
# afficher le dernier mot
if current_word is not None:
    print(f'{current_word}\t{current_count}')
