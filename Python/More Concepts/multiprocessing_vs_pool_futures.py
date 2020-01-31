from concurrent.futures import ProcessPoolExecutor, wait
from multiprocessing    import Pool, cpu_count
import timeit
iteraciones = 12345

def calculo (x):

    divisor = 2

    if x < divisor:

        return []

    factores = []    

    while True:

        if x == 1:
            return factores

        r = x % divisor

        if r == 0:
            factores.append(divisor)
            x = x // divisor
        elif divisor * divisor >= x:
            factores.append(x)
            return factores
        elif divisor > 2:
            # Advance in steps of 2 over odd numbers
            divisor += 2
        else:
            # If p == 2, get to 3
            divisor += 1
    
    return factores 


def iterador_y_tiempo_de_respuesta(respuesta, inicio):
    
    for i in respuesta:
        print(i)
        
    fin = timeit.default_timer()

    print('Time: ', fin - inicio)   


def multiprocesos():
    
    inicio = timeit.default_timer()

    try:
        workers = cpu_count()
    except NotImplementedError:
        workers = 2
    pool = Pool(processes=workers)
    respuesta = pool.map(calculo, range(1, iteraciones))

    # muestre el tiempo de ejecucion
    iterador_y_tiempo_de_respuesta(respuesta, inicio)


def ProcessPool():
    
    inicio = timeit.default_timer()

    try:
        workers = cpu_count()
    except NotImplementedError:
        workers = 2
    pool = ProcessPoolExecutor(max_workers=workers)
    respuesta = pool.map(calculo, range(1, iteraciones))

    # muestre el tiempo de ejecucion
    iterador_y_tiempo_de_respuesta(respuesta, inicio)


if __name__ == '__main__':

    # ejecucion multiprocesos
    multiprocesos()

    print()

    # ejecucion ProcessPoolExecutor
    ProcessPool()