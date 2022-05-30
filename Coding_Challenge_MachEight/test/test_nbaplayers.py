from use_case.nbaplayers import RecoveryData
from config.utils import NO_RECORDS_FOUND_MESSAGE
from config.utils import URL
from config.utils import RESPONSE_EXAMPLE_DATA
from config.utils import RESPONSE_EXAMPLE_DATA_NONE
from config.utils import INDEX_LIST_DATA
from config.utils import INDEX_LIST_DATA_NONE
from config.utils import VALUES_BASE_DATA
from config.utils import VALUES_BASE_DATA_NONE
from config.utils import FILTER_COMBINATION_DATA
from config.utils import FILTER_COMBINATION_DATA_NONE
from config.utils import REQUEST_O_INCHES
from config.utils import REQUEST_139_INCHES

def create_instance_of_recovery_data(inches:int) -> RecoveryData:
    instance = RecoveryData(inches=inches)
    return instance

def test_get_response_data_is_not_none():
    
    instance = create_instance_of_recovery_data(inches=0)
    response = instance.get_response_data(url=URL)
    
    assert response

def test_create_dict_with_index_is_none():
    
    instance = create_instance_of_recovery_data(inches=0)
    response = RESPONSE_EXAMPLE_DATA_NONE
    indexedList = instance.create_dict_with_index(data_reponse=response)
    
    assert not indexedList
        
def test_create_dict_with_index_is_not_none():
    
    instance = create_instance_of_recovery_data(inches=0)
    response = RESPONSE_EXAMPLE_DATA
    indexedList = instance.create_dict_with_index(data_reponse=response)
    
    assert indexedList
 

def test_get_index_with_inches_from_dict_is_none():
        
        instance = create_instance_of_recovery_data(inches=0)
        response = INDEX_LIST_DATA_NONE
        valuesBase = instance.get_index_with_inches_from_dict(indexedList=response)
        
        assert not valuesBase
            
   
def test_get_index_with_inches_from_dict_is_not_none():
        
        instance = create_instance_of_recovery_data(inches=0)
        response = INDEX_LIST_DATA
        valuesBase = instance.get_index_with_inches_from_dict(indexedList=response)
        
        assert valuesBase
        
def test_get_values_from_index_is_none():
        
        instance = create_instance_of_recovery_data(inches=0)
        response = INDEX_LIST_DATA_NONE
        valuesBase = instance.get_values_from_index(indexedList=response)
        
        assert not valuesBase
                
def test_get_values_from_index_is_not_none():
        
        instance = create_instance_of_recovery_data(inches=0)
        response = INDEX_LIST_DATA
        valuesBase = instance.get_values_from_index(indexedList=response)
        
        assert valuesBase


def test_get_combinations_filtered_is_none():
    
    instance = create_instance_of_recovery_data(inches=0)
    response = VALUES_BASE_DATA_NONE
    filterCombinations = instance.get_combinations_filtered(valuesBase=response)
    
    assert not filterCombinations


def test_get_combinations_filtered_is_not_none():
    
    instance = create_instance_of_recovery_data(inches=158)
    response = VALUES_BASE_DATA
    valuesFromIndex = instance.get_values_from_index(indexedList=INDEX_LIST_DATA)    
    filterCombinations = instance.get_combinations_filtered(valuesBase=response)
    
    #assert filterCombinations
    
def test_get_clean_and_order_combinations_is_none():
    
    instance = create_instance_of_recovery_data(inches=158)
    response = FILTER_COMBINATION_DATA_NONE
    cleanCombinations = instance.get_clean_and_order_combinations(filterCombinations=response)
    
    assert not cleanCombinations

def test_get_clean_and_order_combinations_is_not_none():
    
    instance = create_instance_of_recovery_data(inches=139)
    response = FILTER_COMBINATION_DATA
    cleanCombinations = instance.get_clean_and_order_combinations(filterCombinations=response)
    
    assert cleanCombinations
    
def test_get_name_is_none():
    
    instance = create_instance_of_recovery_data(inches=0)
    response = FILTER_COMBINATION_DATA_NONE
    nameCombinations = instance.get_name(value=response)
    
    assert not nameCombinations

def test_get_list_name_is_none():
    
    instance = create_instance_of_recovery_data(inches=0)
    response = FILTER_COMBINATION_DATA_NONE
    nameCombinations = instance.get_list_names(cleanCombinations=response)
    
    assert not nameCombinations
    
def test_get_proccess_data_no_matches_found():
    
    instance = create_instance_of_recovery_data(inches=0)
    response = REQUEST_O_INCHES
    proccessData = instance.get_process_data(url=URL)
    
    assert proccessData == response
    
def test_get_proccess_139_matches_found():
    
    instance = create_instance_of_recovery_data(inches=139)
    response = REQUEST_139_INCHES
    proccessData = instance.get_process_data(url=URL)
    
    assert proccessData == response