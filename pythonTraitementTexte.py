def renvoie(chainecar):
    liste=list(chainecar)
    x=0
    y=0
    i=1
    while i!=len(liste):
        if liste[i]!="\n":
            x+=1
        else:
            del (liste[i])
            y+=1
        i+=1
    return x,y,"".join(liste)
    
print(renvoie("akizkciensixnjjeqjdskdzskzdnv\naizjdozjdkzi\nishbbksdbzdzdbxzbd"))