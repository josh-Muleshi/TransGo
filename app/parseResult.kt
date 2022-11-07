private fun parseResult(jobResponse: List<Job>): MutableList<Job> {
        val jobList = mutableListOf<Job>()

        for (job in jobResponse) {
            jobList.add(job)
        }

        return jobList
    }