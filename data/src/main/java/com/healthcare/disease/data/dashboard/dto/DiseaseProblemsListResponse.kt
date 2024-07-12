package com.healthcare.disease.data.dashboard.dto


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DiseaseProblemsListResponse(
    @SerializedName("problems")
    @Expose
    var problems: List<Problem?>?
)

data class Problem(
    @SerializedName("Asthma")
    @Expose
    var asthma: List<Asthma?>?,
    @SerializedName("Diabetes")
    @Expose
    var diabetes: List<Diabetes?>?
)

data class Asthma(
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("medications")
    @Expose
    var medications: List<Medication?>?
)

data class Diabetes(
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("labs")
    @Expose
    var labs: List<Lab?>?,
    @SerializedName("medications")
    @Expose
    var medications: List<Medication?>?
)

data class Lab(
    @SerializedName("missing_field")
    @Expose
    var missingField: String?
)

data class Medication(
    @SerializedName("medicationsClasses")
    @Expose
    var medicationsClasses: List<MedicationsClasses?>?
)

data class MedicationsClasses(
    @SerializedName("className")
    @Expose
    var className: List<ClassName?>?,
    @SerializedName("className2")
    @Expose
    var className2: List<ClassName?>?
)

data class ClassName(
    @SerializedName("associatedDrug")
    @Expose
    var associatedDrug: List<AssociatedDrug?>?,
    @SerializedName("associatedDrug#2")
    @Expose
    var associatedDrug2: List<AssociatedDrug?>?
)

data class AssociatedDrug(
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("dose")
    @Expose
    var dose: String? = "",
    @SerializedName("name")
    @Expose
    var name: String? = "",
    @SerializedName("strength")
    @Expose
    var strength: String? = "",
    @SerializedName("diseaseId")
    @Expose
    var diseaseId: Int,
)