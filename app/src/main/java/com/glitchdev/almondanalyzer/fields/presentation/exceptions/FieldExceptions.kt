package com.glitchdev.almondanalyzer.fields.presentation.exceptions

open class FieldBaseException: Exception()

class EmptyFieldValuesException: FieldBaseException()
class IncorrectFieldNameException: FieldBaseException()
class IncorrectFieldVarietyException: FieldBaseException()
class IncorrectFieldCadastralException: FieldBaseException()
class IncorrectFieldPlantingYearException: FieldBaseException()
class IncorrectFieldPlantingSchemeException: FieldBaseException()