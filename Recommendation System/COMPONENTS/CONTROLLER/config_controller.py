import logging
from COMPONENTS.CONTROLLER.exceptions         import *
from COMPONENTS.CONTROLLER.routing            import *
from COMPONENTS.CONTROLLER.business_variables import *

LOGGING_HOST   = 'COMPONENTS/CONTROLLER/logging.log'
logging.basicConfig(filename = LOGGING_HOST, level = logging.INFO)